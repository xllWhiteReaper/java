package programming_2.turing_machine.src.turing;

public class Tape {
    private Cell head;
    private Cell tail;
    private Cell currentCell;

    public Tape() {
        head = null;
        tail = null;
        currentCell = null;
    }

    public Tape(Cell cell) {
        pushCell(cell);
    }

    public Tape(Cell... cells) {
        for (Cell cell : cells) {
            pushCell(cell);
        }
    }

    public Cell getCurrentCell() {
        return currentCell;
    }

    public char getContent() {
        return currentCell != null ? currentCell.content : null;
    }

    public void setContent(char character) {
        if (currentCell == null) {
            return;
        }
        currentCell.content = character;
    }

    /**
     * Adds a cell at the start (left) of the tape
     * 
     * @param cell
     */
    private void unshiftCell(Cell cell) {
        if (head == null) {
            head = cell;
            tail = cell;
            currentCell = cell;
            return;
        }
        cell.next = head;
        head.prev = cell;
        head = cell;
    }

    /**
     * Adds a cell at the end (right) of the tape
     * 
     * @param cell
     */
    private void pushCell(Cell cell) {
        if (tail == null) {
            head = cell;
            tail = cell;
            currentCell = cell;
            return;
        }
        cell.prev = tail;
        tail.next = cell;
        tail = cell;
    }

    public void moveLeft() {
        if (currentCell == null) {
            return;
        }
        if (currentCell == head) {
            unshiftCell(new Cell());
        }
        currentCell = currentCell.prev;
    }

    public void moveRight() {
        if (currentCell == null) {
            return;
        }
        if (currentCell == tail) {
            pushCell(new Cell());
        }
        currentCell = currentCell.next;
    }

    public String getTapeContents() {
        Cell headCopy = head;
        String tapeContents = "";
        while (headCopy != null) {
            String charToAdd = headCopy.content == ' ' ? "" : String.valueOf(headCopy.content);
            tapeContents += charToAdd;
            headCopy = headCopy.next;
        }
        return tapeContents;
    }

    public String getTapeContentsWithRecursion() {
        return getTapeContentsWithRecursion(head);
    }

    private String getTapeContentsWithRecursion(Cell head) {
        if (head == null) {
            return "";
        }
        String charToAdd = head.content == ' ' ? "" : String.valueOf(head.content);
        return charToAdd + getTapeContentsWithRecursion(head.next);
    }
}
