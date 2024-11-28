package nl.eleven.adventofcode.tasks;

import jakarta.validation.constraints.NotNull;

public record TaskDto(@NotNull String beanName,
					  String description,
					  @NotNull int year,
					  @NotNull int day,
					  @NotNull String urlTask1,
					  @NotNull String urlTask2,
					  @NotNull String aocUrl) {

}
