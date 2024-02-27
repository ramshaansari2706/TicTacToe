/*Result Dialog Class for the game to display results. */

// Declaring the package.
package com.ramshaansari.tictactoe;

// Importing the required libraries.
import androidx.annotation.NonNull; // Handles null checks.
import androidx.appcompat.app.AlertDialog; // Handles dialog boxes.
import androidx.appcompat.app.AppCompatActivity; // Creates an activity in an Android application.
import android.app.Dialog; // Handles dialogs.
import android.content.Context; // Allows to send data between activities.
import android.content.DialogInterface; // Handles dialog buttons.
import android.os.Bundle; // Handles activity state.
import android.view.View; // Handles UI elements.
import android.widget.Button; // Handles buttons.
import android.widget.TextView; // Handles text views for displaying results.

// ResultDialog class declaration which extends Dialog, indicating that it's a dialog.
public class ResultDialog extends Dialog {

    private final String message; // Variable to store the message to display.
    private final MainActivity mainActivity; // Reference to the MainActivity instance.

    // Constructor for the ResultDialog class.
    public ResultDialog(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context); // Call the superclass's constructor.
        this.message = message; // Initialize the message variable with the provided message.
        this.mainActivity = mainActivity; // Initialize the mainActivity variable with the provided mainActivity.
    }

    // onCreate method to set the content view and handle button clicks.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the superclass's onCreate method.
        setContentView(R.layout.activity_result_dialog); // Set the layout for the dialog.

        TextView messageText = findViewById(R.id.messageText); // Initialize TextView for displaying the message.
        Button startAgainButton = findViewById(R.id.startAgainButton); // Initialize Button for restarting the match.
        Button quitButton = findViewById(R.id.quitButton); // Initialize Button for quitting the game.

        messageText.setText(message); // Set the message text in the TextView.

        // Set click listener for the start again button.
        startAgainButton.setOnClickListener(new View.OnClickListener() {
            // Method called when the start again button is clicked.
            @Override
            public void onClick(View view) {
                mainActivity.restartMatch(); // Call the restartMatch method of the MainActivity instance to restart the match.
                dismiss(); // Dismiss the dialog.
            }
        });

        // Set click listener for the quit button.
        quitButton.setOnClickListener(new View.OnClickListener() {
            // Method called when the quit button is clicked.
            @Override
            public void onClick(View view) {
                showExitConfirmationDialog(); // Call the method to show exit confirmation dialog.
            }
        });
    }

    // Method to show exit confirmation dialog.
    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // Create the builder for the dialog.
        builder.setTitle("Quit Game"); // Set the title for the dialog.
        builder.setMessage("Dear User, Are you sure you wish to quit the game?"); // Set the message for the dialog.
        builder.setCancelable(false); // Disable the back button.
        // Set click listeners for the dialog buttons.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            // Method called when the "Yes" button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mainActivity.finish(); // Close the activity and exit the game.
            }
        });
        // Set click listeners for the dialog buttons.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            // Method called when the "No" button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Dismiss the dialog and resume the game.
            }
        });
        AlertDialog dialog = builder.create(); // Create the dialog.
        dialog.show(); // Show the dialog.
    }
}
// End of Program