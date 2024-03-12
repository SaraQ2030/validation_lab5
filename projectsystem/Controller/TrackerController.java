package org.example.projectsystem.Controller;

import jakarta.validation.Valid;
import org.example.projectsystem.Api.ApiMessage;
import org.example.projectsystem.Model.Tracker;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/track")
public class TrackerController {
    ArrayList<Tracker> trackers=new ArrayList<>();
    @GetMapping("/display")
    public ResponseEntity display(){
        return  ResponseEntity.status(200).body(trackers);
    }

    @PostMapping("/add")
    public ResponseEntity addTracker(@RequestBody @Valid Tracker tracker, Errors errors){

        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        trackers.add(tracker);
        return ResponseEntity.status(200).body(new ApiMessage("data added successfully"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteTrack(@PathVariable int index){
        trackers.remove(index);
        return ResponseEntity.status(200).body(new ApiMessage("Data Deleted"));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateTrack(@PathVariable int index ,@RequestBody @Valid Tracker tracker, Errors errors){
        if (errors.hasErrors()){
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        trackers.set(index,tracker) ;
        return ResponseEntity.status(200).body(new ApiMessage("Updated successflly"));
    }

    @GetMapping("search/title/{tit}")
    public ResponseEntity searchTitle(@PathVariable String tit){
        for (Tracker t:trackers){
            if (t.getTitle().equalsIgnoreCase(tit)){
                return  ResponseEntity.status(200).body("the result :"+"\n"+t);

            }
        }
        return ResponseEntity.status(400).body("the title not found ");
    }

    @GetMapping("change/{index}")
    public ResponseEntity changeStatus(@Valid @PathVariable int index){                                  //Not sure if you asked for change status
        if (trackers.get(index).getStatus().equalsIgnoreCase("Completed")){

            return ResponseEntity.status(200).body("the Status Already Completed");
        }
        else  if (trackers.get(index).getStatus().equalsIgnoreCase("Not Started")) {
               trackers.get(index).setStatus("In Progress");
            return ResponseEntity.status(200).body("the Status Not Started changed to In progress  "+trackers.get(index));

        }else  if (trackers.get(index).getStatus().equalsIgnoreCase("In progress")) {         //change the status of project
            trackers.get(index).setStatus("Completed");
            return ResponseEntity.status(200).body("the Status In progress changed to completed  "+trackers.get(index));

        }
        return ResponseEntity.status(400).body(new ApiMessage("Not found"));
    }

    @GetMapping("comp/{com}")
    public ResponseEntity displayByComName(@PathVariable String com){
        ArrayList<Tracker> company=new ArrayList<>();
        for (Tracker t:trackers){
            if (t.getCompanyName().equalsIgnoreCase(com)){
                company.add(t);
            }
        }
        return ResponseEntity.status(200).body(company);
    }





}
