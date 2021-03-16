/**
 * This class represents a specific location in a 2D map.  Coordinates are
 * integer values.
 **/
public class Location
{
    /** X coordinate of this location. **/
    public int xCoord;

    /** Y coordinate of this location. **/
    public int yCoord;


    /** Creates a new location with the specified integer coordinates. **/
    public Location(int x, int y)
    {
        xCoord = x;
        yCoord = y;
    }

    /** Creates a new location with coordinates (0, 0). **/
    public Location()
    {
        this(0, 0);
    }

    /** Проверка равенства координат **/
    public boolean equals(Object obj) {

        // Является ли объект Location
        if (obj instanceof Location) {
            // Возвращает true, если координаты равны
            Location other = (Location) obj;
            if (this.hashCode() == other.hashCode()) {
                return true;
            }
        }
        // Во всех остальных случая false
        return false;
    }

    /** Генерация хэшкода **/
    public int hashCode() {
        int result = 20; // Любое число
        // Ещё случайные числа для комбиниции
        result = 12 * result + xCoord;
        result = 25 * result + yCoord;
        return result;
    }
}
