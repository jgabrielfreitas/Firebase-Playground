package playground.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PlaygroundActivity extends AppCompatActivity implements ValueEventListener {

    private final String TAG = this.getClass().getSimpleName();
    TextView firebaseMessageTextView;
    String firebaseDefaultMessage = "Hello, World!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);

        firebaseMessageTextView = (TextView) findViewById(R.id.firebaseMessageTextView);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message2");

        myRef.setValue(firebaseDefaultMessage);
        myRef.addValueEventListener(this);

        firebaseMessageTextView.setText(firebaseDefaultMessage);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        String value = dataSnapshot.getValue(String.class);
        firebaseMessageTextView.setText(value);
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        // Failed to read value
        Log.w(TAG, "Failed to read value.", databaseError.toException());
    }
}
