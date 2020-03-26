package data_structures;

public class Triplets<L, M, R> {

    private final L left;
    private final M middle;
    private final R right;

    public Triplets(L left, M middle, R right) {
        assert left != null;
        assert middle != null;
        assert right != null;

        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    public L getLeft() {
        return left;
    }

    public M getMiddle() {
        return middle;
    }

    public R getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ middle.hashCode() ^ right.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Triplets)) return false;
        Triplets t = (Triplets) o;
        return this.left.equals(t.getLeft()) &&
                this.right.equals(t.getRight()) &&
                this.middle.equals(t.getMiddle());
    }

}