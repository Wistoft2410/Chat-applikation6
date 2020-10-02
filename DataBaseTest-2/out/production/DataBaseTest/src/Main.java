import de.bezier.data.sql.*;
import processing.core.PApplet;


public class Main extends PApplet {

    // fjenett 20120226

    SQLite db;

    public static void main(String[] args) {
        PApplet.main("Main");
    }


    @Override
    public void settings() {
        super.settings();
        size(100, 100);
    }

    public void setup(){
        println(dataPath("test.db"));

        db = new SQLite( this, "test.db");  // open database file

        if ( db.connect() )
        {
            String[] tableNames = db.getTableNames();

            db.query( "SELECT * FROM %s", tableNames[0] );

            while (db.next())
            {
                TableOne t = new TableOne();
                db.setFromRow( t );
                println( t );
            }
        }
    }

    class TableOne
    {
        public String fieldOne;
        public int fieldTwo;

        public String toString ()
        {
            return String.format("fieldOne: %s fieldTwo: %d", fieldOne, fieldTwo);
        }
    }

}
