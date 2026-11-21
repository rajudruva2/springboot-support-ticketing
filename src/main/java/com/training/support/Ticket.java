package com.training.support;
import jakarta.persistence.*;import java.time.*;
@Entity @Table(indexes=@Index(columnList="requesterId,status"))
public class Ticket{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;
 public Long requesterId,assigneeId;public String subject,description;
 @Enumerated(EnumType.STRING) public Status status=Status.OPEN;
 @Enumerated(EnumType.STRING) public Priority priority=Priority.MEDIUM;
 @Version public Long version;public Instant createdAt=Instant.now();
 protected Ticket(){} public Ticket(Long r,String s,String d,Priority p){requesterId=r;subject=s;description=d;priority=p;}
 public enum Status{OPEN,IN_PROGRESS,WAITING_ON_CUSTOMER,RESOLVED,CLOSED}
 public enum Priority{LOW,MEDIUM,HIGH,CRITICAL}
}
@Entity class TicketComment{
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) public Long id;public Long ticketId,authorId;
 @Column(length=3000) public String message;public Instant createdAt=Instant.now();
 protected TicketComment(){} TicketComment(Long t,Long a,String m){ticketId=t;authorId=a;message=m;}
}
