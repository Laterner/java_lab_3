import java.util.*;


/**
 * This class stores the basic state necessary for the A* algorithm to compute 
 * a path across a map.  This state includes a collection of "open waypoints" 
 * and another collection of "closed waypoints."  In addition, this class 
 * provides the basic operations that the A* pathfinding algorithm needs to 
 * perform its processing.
 **/
public class AStarState
{
    /** Это ссылка на карту, по которой проходит алгоритм A*. **/
    private Map2D map;

    /** Нестатическое поле класса Map для всех открытых путей **/
    private Map<Location, Waypoint> open_waypoints = new HashMap<Location, Waypoint> ();

    /** Нестатическое поле класса Map для всех закрытых путей **/
    private Map<Location, Waypoint> closed_waypoints = new HashMap<Location, Waypoint> ();

    /** Инициализируйте новый объект состояния для использования алгоритма поиска пути A*. **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("значение map не может быть null");
        this.map = map;
    }

    /** Возвращает карту, по которой проходит алгоритм A*. **/
    public Map2D getMap()
    {
        return map;
    }

    // Получения списка открытых точек
    public Waypoint getMinOpenWaypoint()
    {
        System.out.println("numOpenWaypoints() = " + numOpenWaypoints());
        // Если открытых путевых точек нет, возвращает null.
        if (numOpenWaypoints() == 0)
            return null;

        // Инициализируйте набор ключей всех открытых путевых точек,
        // Iterator для итерации по набору и переменную для хранения лучшей точки
        // и стоимости этой точки.

        Set open_waypoint_keys = open_waypoints.keySet();
        Iterator i = open_waypoint_keys.iterator();
        Waypoint best = null;
        float best_cost = Float.MAX_VALUE;

        // Сканирует все открытые точки.
        while (i.hasNext())
        {
            // Сохраняет текущее местоположение.
            Location location = (Location)i.next();
            // Сохраняет текущую точку.
            Waypoint waypoint = open_waypoints.get(location);
            // Сохраняет общую стоимость для текущей точки.
            float waypoint_total_cost = waypoint.getTotalCost();

            // Если общая стоимость для текущей точки меньше,
            // чем сохраненная стоимость для сохраненной лучшей точки,
            // заменим сохраненную точку текущей точкой,
            // а сохраненную общую стоимость - текущей общей стоимостью.
            if (waypoint_total_cost < best_cost)
            {
                best = open_waypoints.get(location);
                best_cost = waypoint_total_cost;
            }
        }
        // Возвращает точку с минимальной стоимостью.
        return best;
    }

    /** Добавить точку в список открытых точек, или обновить её значения, если имеются **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Находит местоположение новой точки.
        Location location = newWP.getLocation();

        // Проверяет, есть ли в списке открытых точек текущая точка.
        if (open_waypoints.containsKey(location))
        {
            // Если в списке открытых точек уже есть эта точка,
            // проверяет, не меньше ли значение "предыдущей стоимости" новой точки,
            // чем значение "предыдущей стоимости" текущей путевой точки.
            Waypoint current_waypoint = open_waypoints.get(location);
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                // Если значение предыдущей стоимости новой точки меньше,
                // чем «предыдущая стоимость» текущей точки,
                // новая путевая точка заменяет старую путевую точку и возвращает истину.
                open_waypoints.put(location, newWP);
                return true;
            }
            // Если значение предыдущей стоимости новой точки не меньше,
            // чем предыдущая стоимость текущей точки, вернуть false.
            return false;
        }
        // Если текущей точки ещё нет в списке открытых, то
        // добавим её туда и вернём true.
        open_waypoints.put(location, newWP);
        return true;
    }


    /** Возвращает текущее количество открытых точек. **/
    public int numOpenWaypoints()
    {
        return open_waypoints.size();
    }

    /** Этот метод перемещает точку из открытого списка в закрытый. **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = open_waypoints.remove(loc);
        closed_waypoints.put(loc, waypoint);
    }

    /** Возвращает true, если список закрытых точек содержит точку для указанного Location. **/
    public boolean isLocationClosed(Location loc)
    {
        return closed_waypoints.containsKey(loc);
    }
}