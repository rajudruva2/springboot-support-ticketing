package com.training.support;
import org.springframework.data.jpa.repository.JpaRepository;import java.util.*;
public interface TicketRepository extends JpaRepository<Ticket,Long>{List<Ticket> findByRequesterIdOrderByCreatedAtDesc(Long requesterId);}
interface TicketCommentRepository extends JpaRepository<TicketComment,Long>{List<TicketComment> findByTicketIdOrderByCreatedAtAsc(Long ticketId);}
