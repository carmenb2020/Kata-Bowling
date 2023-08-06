package org.example.Game;
import org.junit.jupiter.api.Test;
import java.util.Random;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game = new Game();
    @Test
    public void write_a_class_game_with_two_methods(){
        Method[] allMethods = Game.class.getDeclaredMethods();
        assertTrue(allMethods.length>=2 );
    }

    @Test
    public void method_Roll_the_argument_is_the_number_of_pins_knocked_down(){
        int numArguments=0;
        try {
            Method method = Game.class.getMethod("Roll", int.class);
            numArguments = method.getParameterCount();
            assertEquals(2, numArguments);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void score_is_called_only_at_the_very_end_of_the_game_It_returns_the_total_score_for_that_game(){
        try {
            Method method = Game.class.getMethod("Score");
            Class<?> returnType = method.getReturnType();
            assertTrue(returnType.equals(Integer.class) || returnType.equals(int.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void a_completed_game_of_bowling_consists_of_10_frames(){
        List<Integer> frames = new ArrayList<>();
        for (int i = 0; i<=10; i++){
            int frameScore = ThreadLocalRandom.current().nextInt(0, 11);
            frames.add(frameScore);
        }
        game.setFramesGame(frames);
        int score = game.Score();
        assertNotNull(score);
    }

    @Test
    public void in_each_frame_the_player_has_two_opportunities_to_knock_down_10_pins(){
        game.Roll(5,1);
        game.Roll(3,2);
        assertEquals(1,game.framesGame.size());
    }

    @Test
    public void the_score_for_the_frame_is_the_total_number_of_pins_knocked_down_plus_bonuses_for_strikes(){
        game.Roll(10,1);
        game.Roll(2,1);
        game.Roll(3,2);
        assertEquals(15,game.framesGame.get(0));
    }

    @Test
    public void the_score_for_the_frame_is_the_total_number_of_pins_knocked_down_plus_bonuses_for_spares(){
        game.Roll(1,1);
        game.Roll(2,2);
        game.Roll(5,1);
        game.Roll(5,2);
        game.Roll(4,1);
        game.Roll(3,2);
        assertEquals(14,game.framesGame.get(1));
    }

    @Test
    public void in_the_tenth_frame_a_player_who_rolls_a_spare_or_strike_is_allowed_to_roll_the_extra_balls_to_complete_the_frame(){
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(10,1);
        game.Roll(5,1);
        game.Roll(5,2);
        assertEquals(20,game.framesGame.get(9));

    }

    @Test
    public void no_exists_more_tenth_frame_in_every_game_when_exception_thrown_then_assert_exception() {
        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            game.framesGame.get(11);;
        });
        String actualMessage = exception.getMessage();
        assertNotNull(actualMessage);
    }
}