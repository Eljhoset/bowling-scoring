package com.eljhoset.bowlingscoring.parser.mapper;

import com.eljhoset.bowlingscoring.parser.model.FrameRolls;
import com.eljhoset.bowlingscoring.parser.model.Player;
import com.eljhoset.bowlingscoring.parser.model.PlayerFrames;
import com.eljhoset.bowlingscoring.parser.model.Roll;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import org.junit.After;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.Test;
import com.eljhoset.bowlingscoring.parser.model.FrameList;

/**
 *
 * @author jd-jd
 */
public class DefaultPlayerFramelMapperTest {

    private static final String FILE_PATH_REGULAR_GAME = "src/test/resources/regular_game.txt";
    private static final String FILE_PATH_REGULAR_GAME_TWO_PLAYERS = "src/test/resources/regular_game_two_players.txt";
    private static final String FILE_PATH_ALL_STRIKE = "src/test/resources/all_strikes.txt";
    private PlayerFramelMapper mapper;
    private List<String> regularGameOnePlayer;
    private List<String> allStrikesGameOnePlayer;
    private List<String> regularGameTwoPlayer;

    @Before
    public void setup() throws Exception {
        this.mapper = new DefaultPlayerFramelMapper();
        this.regularGameOnePlayer = Files.readAllLines(Paths.get(FILE_PATH_REGULAR_GAME));
        this.regularGameTwoPlayer = Files.readAllLines(Paths.get(FILE_PATH_REGULAR_GAME_TWO_PLAYERS));
        this.allStrikesGameOnePlayer = Files.readAllLines(Paths.get(FILE_PATH_ALL_STRIKE));

    }

    @After
    public void tearDown() {
        this.mapper = null;
        this.regularGameOnePlayer = null;
        this.regularGameTwoPlayer = null;
        this.allStrikesGameOnePlayer = null;
    }

    @Test(expected = NullPointerException.class)
    public void map_nullPathParam_throwException() throws Exception {
        this.mapper.map(null);
    }

    @Test
    public void map_regularGameOnePlayer_returnOnePlayerFrames() {
        List<PlayerFrames> frames = this.mapper.map(regularGameOnePlayer);
        assertThat(frames, hasSize(1));
    }

    @Test
    public void map_regularGameTwoPlayer_returnOnePlayerFrames() {
        List<PlayerFrames> frames = this.mapper.map(regularGameTwoPlayer);
        assertThat(frames, hasSize(2));
    }

    @Test
    public void map_regularGameOnePlayer_returnCorrectPlayerName() {
        final List<PlayerFrames> framesList = this.mapper.map(regularGameOnePlayer);
        final PlayerFrames playerFrames = framesList.get(0);
        final Player player = playerFrames.getPlayer();

        assertThat(player.getName(), is("Jose"));
    }

    @Test
    public void map_regularGameTwoPlayer_returnCorrectPlayerName() {
        final List<PlayerFrames> framesList = this.mapper.map(regularGameTwoPlayer);
        List<Player> players = framesList.stream().map(PlayerFrames::getPlayer).collect(Collectors.toList());

        assertThat(players, containsInAnyOrder(
                hasProperty("name", is("Jose")),
                hasProperty("name", is("Daniel"))
        ));
    }

    @Test
    public void map_regularGameOnePlayer_returnCorrectFrameCount() {
        final List<PlayerFrames> framesList = this.mapper.map(regularGameOnePlayer);
        final PlayerFrames playerFrames = framesList.get(0);
        final FrameList frames = playerFrames.getFrames();

        assertThat(frames.getFrames().size(), is(10));
    }

    @Test
    public void map_regularGameTwoPlayer_returnCorrectFrameCount() {
        final List<PlayerFrames> framesList = this.mapper.map(regularGameTwoPlayer);
        List<Integer> list = framesList.stream().map(PlayerFrames::getFrames)
                .map(e -> e.getFrames())
                .map(List::size)
                .collect(Collectors.toList());

        assertThat(list, contains(10, 10));
    }

    @Test
    public void map_regularGameOnePlayer_returnCorrectRollNumbers() {
        final List<PlayerFrames> framesList = this.mapper.map(regularGameOnePlayer);
        final PlayerFrames playerFrames = framesList.get(0);
        final FrameList frames = playerFrames.getFrames();
        List<Roll> rools = frames.getFrames().stream()
                .map(e -> e.rolls())
                .map(FrameRolls::getRolls)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat(rools.size(), is(17));
    }

    @Test
    public void map_regularGameTwoPlayer_returnCorrectRollNumbers() {
        final List<PlayerFrames> framesList = this.mapper.map(regularGameTwoPlayer);
        List<FrameList> frames = framesList.stream().map(PlayerFrames::getFrames)
                .collect(Collectors.toList());

        Long rolls = frames.get(0).getFrames().stream()
                .map(e -> e.rolls())
                .map(FrameRolls::getRolls)
                .flatMap(List::stream)
                .collect(Collectors.counting());

        Long rolls2 = frames.get(1).getFrames().stream()
                .map(e -> e.rolls())
                .map(FrameRolls::getRolls)
                .flatMap(List::stream)
                .collect(Collectors.counting());

        assertThat(Arrays.asList(rolls, rolls2), contains(18l, 17l));
    }

    @Test
    public void map_allStrikeGameOnePlayer_returnCorrectRollNumbers() {
        final List<PlayerFrames> framesList = this.mapper.map(allStrikesGameOnePlayer);
        final PlayerFrames playerFrames = framesList.get(0);
        final FrameList frames = playerFrames.getFrames();
        List<Roll> rools = frames.getFrames().stream()
                .map(e -> e.rolls())
                .map(FrameRolls::getRolls)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat(rools.size(), is(12));
    }

}
