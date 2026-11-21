package com.training.support;
import jakarta.validation.constraints.*;
public record TicketRequest(@NotNull @Positive Long requesterId,@NotBlank @Size(max=180) String subject,@NotBlank @Size(max=4000) String description,@NotNull Ticket.Priority priority){}
