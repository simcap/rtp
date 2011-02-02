package fr.rtp.simplification.compmethod;

/**
 * Chapter 7 - Simplification
 * Compose Method
 *
 * Refactoring - Simplify the method add.
 */
public class CustomList {

  private boolean readOnly;
  private Object[] elements;
  private int size;

  public CustomList() {
    this.elements = new Object[0];
    this.size = 0;
    this.readOnly = true;
  }

  public void add(Object element) {
    if (!readOnly) {
      int newSize = size + 1;
      if (newSize > elements.length) {
        Object[] newElements = new Object[elements.length + 10];
        for (int i = 0; i < size; i++) {
          newElements[i] = elements[i];
        }
        elements = newElements;
      }
      elements[size++] = element;
    }
  }

  public int getSize() {
    return size;
  }

  public Object getElement(int index) {
    return elements[index];
  }

  public void setReadOnly(boolean readOnly) {
    this.readOnly = readOnly;
  }

}
