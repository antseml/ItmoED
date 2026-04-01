package transport;

import humans.Human;
import interfaces.Transportable;
import util.Coordinates;
import java.util.ArrayList;
import java.util.List;

public class Galosha implements Transportable {
    private Coordinates position;
    private boolean isAutoMode;
    private int speed;
    private List<Human> passengers;
    private CourseGraph courseGraph;

    public Galosha(Coordinates startPosition, int speed) {
        this.position = startPosition;
        this.speed = speed;
        this.isAutoMode = false;
        this.passengers = new ArrayList<>();
        this.courseGraph = new CourseGraph();
        System.out.println("Галоша готова к поездке.");
    }

    @Override
    public Coordinates move(Coordinates target) {
        return move(target, this.speed);
    }

    public Coordinates move(Coordinates target, int speed) {
        System.out.println("Галоша движется дальше. Все маневры повторяем: останавливаемся, повисим немного и дальше.");
        this.position = target;
        
        if (isAutoMode) {
            courseGraph.recordRoute(target, target);
        }
        
        return this.position;
    }

    @Override
    public Coordinates getCurrentPosition() {
        return position;
    }

    public Coordinates goBack(boolean precise) {
        if (courseGraph.isCalibrated()) {
            System.out.println("Из Зоны 'галоша' сама везет. Есть у нее такое устройство, курсограф, что ли, которое «галошу» точно по тому же курсу обратно ведет, по которому сюда привели.");
            courseGraph.followRoute(this);
        } else {
            System.out.println("Курсограф не откалиброван, плывем наугад.");
        }
        return position;
    }

    public Galosha stopAndHover(int duration) {
        System.out.println("Останавливаемся, повисим немного...");
        return this;
    }

    public Galosha board(List<Human> group) {
        for (Human human : group) {
            if (!passengers.contains(human)) {
                passengers.add(human);
                System.out.println(human.getName() + " забрался в Галошу.");
            }
        }
        return this;
    }

    public Galosha disembark(List<Human> group) {
        for (Human human : group) {
            passengers.remove(human);
            System.out.println(human.getName() + " вышел из Галоши.");
        }
        return this;
    }

    public Galosha startAutoReturn() {
        this.isAutoMode = true;
        System.out.println("Включаем автопилот. Галоша запомнила путь обратно.");
        return this;
    }

    //внтурений
    public class CourseGraph {
        private List<Coordinates> route;
        private boolean isCalibrated;

        public CourseGraph() {
            this.route = new ArrayList<>();
            this.isCalibrated = false;
            System.out.println("Курсограф активирован.");
        }

        public CourseGraph recordRoute(Coordinates start, Coordinates end) {
            route.add(start);
            route.add(end);
            this.isCalibrated = true;
            System.out.println("Курсограф записал маршрут.");
            return this;
        }

        public Coordinates getNextPoint(Coordinates currentPosition) {
            if (route.isEmpty()) {
                return currentPosition;
            }
            
            Coordinates next = route.get(route.size() - 1);
            route.remove(route.size() - 1);
            return next;
        }

        public Galosha followRoute(Galosha galosha) {
            System.out.println("Плывем обратно, все маневры повторяем...");
            
            List<Coordinates> reverseRoute = new ArrayList<>(route);
            for (int i = reverseRoute.size() - 1; i >= 0; i--) {
                Coordinates point = reverseRoute.get(i);
                galosha.move(point, galosha.speed);
                galosha.stopAndHover(1);
            }
            
            System.out.println("Над всеми моими гайками проходим, хоть собирай их.");
            return galosha;
        }

        public boolean isCalibrated() { return isCalibrated; }
    }

    public Coordinates getPosition() { return position; }
    public boolean isAutoMode() { return isAutoMode; }
    public int getSpeed() { return speed; }
    public List<Human> getPassengers() { return passengers; }
    public CourseGraph getCourseGraph() { return courseGraph; }
}