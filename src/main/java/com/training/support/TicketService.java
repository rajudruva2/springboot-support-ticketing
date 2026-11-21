package com.training.support;
import org.springframework.stereotype.*;import org.springframework.transaction.annotation.*;import java.util.*;
@Service public class TicketService{
 private final TicketRepository r;private final TicketCommentRepository c;
 public TicketService(TicketRepository r,TicketCommentRepository c){this.r=r;this.c=c;}
 @Transactional public Ticket create(TicketRequest x){return r.save(new Ticket(x.requesterId(),x.subject(),x.description(),x.priority()));}
 @Transactional(readOnly=true) public Ticket get(long id){return r.findById(id).orElseThrow(()->new IllegalArgumentException("Ticket not found"));}
 @Transactional public Ticket assign(long id,long agent){var t=get(id);if(t.status==Ticket.Status.CLOSED)throw new IllegalStateException("Closed ticket cannot be assigned");t.assigneeId=agent;return t;}
 @Transactional public Ticket transition(long id,Ticket.Status target){var t=get(id);boolean ok=switch(t.status){case OPEN->target==Ticket.Status.IN_PROGRESS||target==Ticket.Status.CLOSED;case IN_PROGRESS->target==Ticket.Status.WAITING_ON_CUSTOMER||target==Ticket.Status.RESOLVED;case WAITING_ON_CUSTOMER->target==Ticket.Status.IN_PROGRESS||target==Ticket.Status.RESOLVED;case RESOLVED->target==Ticket.Status.CLOSED||target==Ticket.Status.IN_PROGRESS;case CLOSED->false;};if(!ok)throw new IllegalStateException("Invalid ticket status transition");t.status=target;return t;}
 @Transactional public TicketComment comment(long id,CommentRequest x){get(id);return c.save(new TicketComment(id,x.authorId(),x.message()));}
 @Transactional(readOnly=true) public List<TicketComment> comments(long id){get(id);return c.findByTicketIdOrderByCreatedAtAsc(id);}
 @Transactional(readOnly=true) public List<Ticket> byRequester(long id){return r.findByRequesterIdOrderByCreatedAtDesc(id);}
}
