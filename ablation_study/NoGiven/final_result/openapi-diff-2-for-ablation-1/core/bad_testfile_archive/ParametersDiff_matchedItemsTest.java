package org.openapitools.openapidiff.core.compare;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

public class ParametersDiff_matchedItemsTest {

  @Test
  public void testMatchedItems_SingleMatch() {
    ParametersDiff parametersDiff =
        new ParametersDiff(null) {
          @Override
          public ArrayList<String> matchedItems(String string, String regex) {
            Matcher matcher = Pattern.compile(regex).matcher(string);
            ArrayList<String> matchedItems = new ArrayList<>();
            while (matcher.find()) {
              String item = matcher.group();
              matchedItems.add(item.substring(1, item.length() - 1));
            }
            return matchedItems;
          }
        };
    String string = "/path/{param1}";
    String regex = "\\{([^/]+)}";
    ArrayList<String> expected = new ArrayList<>();
    expected.add("param1");

    ArrayList<String> result = parametersDiff.matchedItems(string, regex);
    assertEquals(expected, result);
  }

  @Test
  public void testMatchedItems_MultipleMatches() {
    ParametersDiff parametersDiff =
        new ParametersDiff(null) {
          @Override
          public ArrayList<String> matchedItems(String string, String regex) {
            Matcher matcher = Pattern.compile(regex).matcher(string);
            ArrayList<String> matchedItems = new ArrayList<>();
            while (matcher.find()) {
              String item = matcher.group();
              matchedItems.add(item.substring(1, item.length() - 1));
            }
            return matchedItems;
          }
        };
    String string = "/path/{param1}/another/{param2}";
    String regex = "\\{([^/]+)}";
    ArrayList<String> expected = new ArrayList<>();
    expected.add("param1");
    expected.add("param2");

    ArrayList<String> result = parametersDiff.matchedItems(string, regex);
    assertEquals(expected, result);
  }

  @Test
  public void testMatchedItems_NoMatch() {
    ParametersDiff parametersDiff =
        new ParametersDiff(null) {
          @Override
          public ArrayList<String> matchedItems(String string, String regex) {
            Matcher matcher = Pattern.compile(regex).matcher(string);
            ArrayList<String> matchedItems = new ArrayList<>();
            while (matcher.find()) {
              String item = matcher.group();
              matchedItems.add(item.substring(1, item.length() - 1));
            }
            return matchedItems;
          }
        };
    String string = "/path/noParams";
    String regex = "\\{([^/]+)}";
    ArrayList<String> expected = new ArrayList<>();

    ArrayList<String> result = parametersDiff.matchedItems(string, regex);
    assertEquals(expected, result);
  }
}
