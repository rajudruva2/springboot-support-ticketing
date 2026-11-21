package com.training.support;
import jakarta.validation.constraints.*;
public record CommentRequest(@NotNull @Positive Long authorId,@NotBlank @Size(max=3000) String message){}
