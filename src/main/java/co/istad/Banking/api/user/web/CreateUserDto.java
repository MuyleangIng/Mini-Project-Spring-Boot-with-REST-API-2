package co.istad.Banking.api.user.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.NonNull;

@Builder
public record CreateUserDto(
                            @NotBlank(message = "Name is Required.....!")  String name,
                            @NotBlank(message = "gender is Required...... !") String gender,
                            String oneSignalId,
                            String studentCardId,
                            @NotNull(message = "You have to confirm are you a student ...for sure")

                            Boolean isStudent
                             ) {
}
