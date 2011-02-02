package fr.rtp.simplification.compmethod;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CustomListTest {

    @Test
    public void should_not_add_element_to_read_only_list() throws Exception {
        CustomList customList = new CustomList();
        Object element = new Object();
        customList.add(element);
        assertEquals(0, customList.getSize());
    }

    @Test
    public void should_add_one_element_to_not_read_only_list() throws Exception {
        CustomList customList = new CustomList();
        customList.setReadOnly(false);
        Object element = new Object();
        customList.add(element);
        assertEquals(1, customList.getSize());
        assertSame(element, customList.getElement(0));
    }

    @Test
    public void should_add_more_than_one_element_to_not_read_only_list() throws Exception {
        CustomList customList = new CustomList();
        customList.setReadOnly(false);
        Object elementOne = new Object();
        Object elementTwo = new Object();
        Object elementThree = new Object();
        customList.add(elementOne);
        customList.add(elementTwo);
        customList.add(elementThree);
        assertEquals(3, customList.getSize());
        assertSame(elementOne, customList.getElement(0));
        assertSame(elementTwo, customList.getElement(1));
        assertSame(elementThree, customList.getElement(2));
    }
}
