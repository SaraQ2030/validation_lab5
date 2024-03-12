package org.example.event2.Controller;

import jakarta.validation.Valid;
import org.example.event2.Api.ApiMessage;
import org.example.event2.Model.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

    @RestController
    @RequestMapping("event")
    public class EventController {
        ArrayList<Event> events=new ArrayList<>();
        @GetMapping("/display")
        public ResponseEntity display(){

            return ResponseEntity.status(200).body(events);
        }

        @PostMapping("/add")
        public ResponseEntity addEvent(@RequestBody @Valid Event event, Errors errors){
            if (errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            events.add(event);
            return ResponseEntity.status(200).body(new ApiMessage("Event added successfully"));
        }




        @PutMapping("/update/{index}")
        public ResponseEntity updateEvent(@PathVariable int index,@RequestBody @Valid Event event,Errors errors){
            if (errors.hasErrors()){
                String message=errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            events.set(index,event);
            return ResponseEntity.status(200).body(new ApiMessage("Event added successfully"));
        }


        @DeleteMapping("/delete/{index}")
        public ResponseEntity deleteEvent(@PathVariable int index){
            events.remove(index);
            return ResponseEntity.status(200).body(new ApiMessage("Event deleted"));
        }


        @PutMapping("/change/{cap}/{id}")
        public ResponseEntity changeCapacity( @Valid @PathVariable String cap,@PathVariable String id){
            for(Event e:events){
                if (e.getId().equals(id)) {
                    if (Integer.parseInt(cap)>25){
                    e.setCapacity(cap);
                    return ResponseEntity.status(200).body(new ApiMessage("Capacity changed" + e));
                }
                    else return ResponseEntity.status(400).body(new ApiMessage("Capacity is should be more than 25 "));
           }
            }
            return ResponseEntity.status(400).body(new ApiMessage("the event id not exist ")) ;
        }

        @GetMapping("/search/{id}")
        public ResponseEntity searchById(@PathVariable String id){
            for (Event e:events){
                if (e.getId().equals(id)){
                    return ResponseEntity.status(200).body(new ApiMessage("The event :"+e));
                }
            }
            return ResponseEntity.status(400).body(new ApiMessage("The event not exist in the system"));
        }
    }
