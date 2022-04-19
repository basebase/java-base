package cn.xmy.base.ch03;

/**
 * @Author xiaomoyu
 * @Date: 2022/4/19 16:47:08
 * @Description:    判断当前是否是一个Immutable类
 */
public class Line {
    private final Point startPoint;
    private final Point endPoint;

    // 该方法导致Line类不能称为Immutable
//    public Line(Point startPoint, Point endPoint) {
//        this.startPoint = startPoint;
//        this.endPoint = endPoint;
//    }

    // 内部重新创建新对象, 防止外部point类进行修改
    public Line(Point startPoint, Point endPoint) {
        this.startPoint = new Point(startPoint.x, startPoint.y);
        this.endPoint = new Point(endPoint.x, endPoint.y);
    }

    public Line(int startx, int starty, int endx, int endy) {
        this.startPoint = new Point(startx, starty);
        this.endPoint = new Point(endx, endy);
    }

    public int getStartX() {
        return startPoint.getX();
    }

    public int getStartY() {
        return startPoint.getY();
    }

    public int getEndX() {
        return endPoint.getX();
    }

    public int getEndY() {
        return endPoint.getY();
    }

    @Override
    public String toString() {
        return "Line{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }
}
