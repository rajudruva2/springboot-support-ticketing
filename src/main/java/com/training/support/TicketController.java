package com.training.support;
import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;import org.springframework.http.*;
@RestController @RequestMapping("/api/v1/tickets") public class TicketController{
 private final TicketService s;public TicketController(TicketService s){this.s=s;}
 @PostMapping public ResponseEntity<Ticket> create(@Valid @RequestBody TicketRequest x){return ResponseEntity.status(201).body(s.create(x));}
 @GetMapping("/{id}") public Ticket get(@PathVariable long id){return s.get(id);}
 @PatchMapping("/{id}/assign") public Ticket assign(@PathVariable long id,@RequestParam long agentId){return s.assign(id,agentId);}
 @PatchMapping("/{id}/status") public Ticket status(@PathVariable long id,@RequestParam Ticket.Status target){return s.transition(id,target);}
 @PostMapping("/{id}/comments") public TicketComment comment(@PathVariable long id,@Valid @RequestBody CommentRequest x){return s.comment(id,x);}
 @GetMapping("/{id}/comments") public java.util.List<TicketComment> comments(@PathVariable long id){return s.comments(id);}
 @GetMapping public java.util.List<Ticket> byRequester(@RequestParam long requesterId){return s.byRequester(requesterId);}
}
