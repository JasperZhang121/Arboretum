package comp1110.ass2;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class isCardAdjacentToAnotherCardTest {

    private String errorPrefix(String[][] state) {
        return "Arboretum.isCardAdjacentToAnotherCard(" + System.lineSeparator() + "sharedState: " + Arrays.toString(state[0]) + System.lineSeparator() +
                "hiddenState: " + Arrays.toString(state[1]) + ")"
                + System.lineSeparator();
    }

    private void test(String[][] state, String arboretum, String placement, boolean expected) {
        String errorPrefix = errorPrefix(state);
        boolean out = Arboretum.isCardAdjacentToAnotherCard(arboretum,placement);
        assertEquals(expected, out, errorPrefix);
    }

    @Test
    public void isAdjacentTest() {
        String[][] state = {{"A", "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01c8N02W02c1S01C00b6N03E01m8N03W01m1S02E01a8N04E01c2N01W01", "Aa3",
                            "Bj5C00C00j6S01C00j7S01W01j4C00W01m6C00E01m3C00W02j3N01W01d2N02W01d7S02C00b8S02E01b3N01C00d1N03W01d8S03C00b2N02C00j8S01E01", "Bd4c4"},
                            {"", "Aa7b4c5c7d3d5j1m5", "Ba1a6b5b7d6j2m2"}};
        String arboretum = "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01c8N02W02c1S01C00b6N03E01m8N03W01m1S02E01a8N04E01c2N01W01";
        String placement = "a7S01W01";
        boolean expected = true;
        test(state, arboretum, placement, expected);
    }

    @Test
    public void isNotAdjacentTest() {
        String[][] state = {{"A", "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01c8N02W02c1S01C00b6N03E01m8N03W01m1S02E01a8N04E01c2N01W01", "Aa3",
                            "Bj5C00C00j6S01C00j7S01W01j4C00W01m6C00E01m3C00W02j3N01W01d2N02W01d7S02C00b8S02E01b3N01C00d1N03W01d8S03C00b2N02C00j8S01E01", "Bd4c4"},
                            {"", "Aa7b4c5c7d3d5j1m5", "Ba1a6b5b7d6j2m2"}};
        String arboretum = "Ab1C00C00a4N01C00c3C00E01c6N02C00m7N02W01m4N01E01a5N02E01a2S01E01c8N02W02c1S01C00b6N03E01m8N03W01m1S02E01a8N04E01c2N01W01";
        String placement = "a7S03N08";
        boolean expected = false;
        test(state, arboretum, placement, expected);
    }
}
