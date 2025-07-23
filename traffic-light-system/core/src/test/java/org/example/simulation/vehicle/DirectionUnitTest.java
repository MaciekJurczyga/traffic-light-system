package org.example.simulation.vehicle;

import org.example.simulation.intersection.TurnType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class DirectionUnitTest {

    @Test
    @DisplayName("fromString() should return correct enum value")
    void fromStringShouldReturnCorrectEnumValue() {
        assertThat(Direction.fromString("north")).isEqualTo(Direction.NORTH);
        assertThat(Direction.fromString("EAST")).isEqualTo(Direction.EAST);
        assertThat(Direction.fromString("South")).isEqualTo(Direction.SOUTH);
        assertThat(Direction.fromString(" west ")).isEqualTo(Direction.WEST);
    }

    @Test
    @DisplayName("fromString() should throw exception on invalid input")
    void fromStringShouldThrowOnInvalidInput() {
        assertThatThrownBy(() -> Direction.fromString("invalid"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Unknown direction");
    }

    @Test
    @DisplayName("fromString() should throw exception on null input")
    void fromStringShouldThrowOnNullInput() {
        assertThatThrownBy(() -> Direction.fromString(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("cannot be null");
    }

    @Test
    @DisplayName("getTurnType() should return LEFT when turning left")
    void getTurnTypeShouldReturnLeft() {
        assertThat(Direction.NORTH.getTurnType(Direction.EAST)).isEqualTo(TurnType.LEFT);
        assertThat(Direction.EAST.getTurnType(Direction.SOUTH)).isEqualTo(TurnType.LEFT);
        assertThat(Direction.SOUTH.getTurnType(Direction.WEST)).isEqualTo(TurnType.LEFT);
        assertThat(Direction.WEST.getTurnType(Direction.NORTH)).isEqualTo(TurnType.LEFT);
    }

    @Test
    @DisplayName("getTurnType() should return RIGHT when turning right")
    void getTurnTypeShouldReturnRight() {
        assertThat(Direction.NORTH.getTurnType(Direction.WEST)).isEqualTo(TurnType.RIGHT);
        assertThat(Direction.WEST.getTurnType(Direction.SOUTH)).isEqualTo(TurnType.RIGHT);
        assertThat(Direction.SOUTH.getTurnType(Direction.EAST)).isEqualTo(TurnType.RIGHT);
        assertThat(Direction.EAST.getTurnType(Direction.NORTH)).isEqualTo(TurnType.RIGHT);
    }

    @Test
    @DisplayName("getTurnType() should return STRAIGHT when going forward")
    void getTurnTypeShouldReturnStraight() {
        assertThat(Direction.NORTH.getTurnType(Direction.SOUTH)).isEqualTo(TurnType.STRAIGHT);
        assertThat(Direction.SOUTH.getTurnType(Direction.NORTH)).isEqualTo(TurnType.STRAIGHT);
        assertThat(Direction.EAST.getTurnType(Direction.WEST)).isEqualTo(TurnType.STRAIGHT);
        assertThat(Direction.WEST.getTurnType(Direction.EAST)).isEqualTo(TurnType.STRAIGHT);
    }

    @Test
    @DisplayName("getTurnType() should throw on same from/to direction")
    void getTurnTypeShouldThrowOnInvalidSameDirection() {
        assertThatThrownBy(() -> Direction.NORTH.getTurnType(Direction.NORTH))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Invalid turn");
    }

    @Test
    @DisplayName("getTurnType() should throw when 'to' is null")
    void getTurnTypeShouldThrowOnNullTo() {
        assertThatThrownBy(() -> Direction.NORTH.getTurnType(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("cannot be null");
    }

    @Test
    @DisplayName("opposite() should return correct direction")
    void oppositeShouldReturnCorrectDirection() {
        assertThat(Direction.NORTH.opposite()).isEqualTo(Direction.SOUTH);
        assertThat(Direction.SOUTH.opposite()).isEqualTo(Direction.NORTH);
        assertThat(Direction.EAST.opposite()).isEqualTo(Direction.WEST);
        assertThat(Direction.WEST.opposite()).isEqualTo(Direction.EAST);
    }

    @Test
    @DisplayName("leftFromIntersection() and rightFromIntersection() should return correct values")
    void leftAndRightFromIntersectionShouldBeCorrect() {
        assertThat(Direction.NORTH.leftFromIntersection()).isEqualTo(Direction.EAST);
        assertThat(Direction.NORTH.rightFromIntersection()).isEqualTo(Direction.WEST);

        assertThat(Direction.EAST.leftFromIntersection()).isEqualTo(Direction.SOUTH);
        assertThat(Direction.EAST.rightFromIntersection()).isEqualTo(Direction.NORTH);

        assertThat(Direction.SOUTH.leftFromIntersection()).isEqualTo(Direction.WEST);
        assertThat(Direction.SOUTH.rightFromIntersection()).isEqualTo(Direction.EAST);

        assertThat(Direction.WEST.leftFromIntersection()).isEqualTo(Direction.NORTH);
        assertThat(Direction.WEST.rightFromIntersection()).isEqualTo(Direction.SOUTH);
    }
}
