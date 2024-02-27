/*The AddPlayers Class to add players in the game */

// Declaring the package.
package com.ramshaansari.tictactoe;

// Importing the required libraries.
import androidx.appcompat.app.AlertDialog; // Handles dialog boxes.
import androidx.appcompat.app.AppCompatActivity; // Creates an activity in an Android application.
import android.content.DialogInterface; //  Handles dialog boxes.
import android.content.Intent; // Allows to send data between activities.
import android.os.Bundle; // Handles activity state.
import android.view.View; // Handles UI elements.
import android.widget.Button; // Handles buttons.
import android.widget.EditText; // Handles input fields.
import android.widget.Toast; // Handles short-lived messages.

// AddPlayers class declaration which extends AppCompatActivity, indicating that it's an activity in an Android application.
public class AddPlayers extends AppCompatActivity {
    // onCreate method to set the content view and handle button clicks.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the superclass's onCreate method.
        setContentView(R.layout.activity_add_players); // Set the layout for the activity.

        EditText playerOne = findViewById(R.id.playerOne); // Initialize the player one EditText.
        EditText playerTwo = findViewById(R.id.playerTwo); // Initialize the player two EditText.
        Button startGameButton = findViewById(R.id.startGameButton); // Initialize the start game button.
        Button helpButton = findViewById(R.id.helpButton);

        // Set click listener for start game button.
        startGameButton.setOnClickListener(new View.OnClickListener() {
            // Method called when start game button is clicked.
            @Override
            public void onClick(View view) {

                String getPlayerOneName = playerOne.getText().toString(); // Get player one name from EditText.
                String getPlayerTwoName = playerTwo.getText().toString(); // Get player two name from EditText.

                // Check if player names are empty.
                if (getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()) {
                    Toast.makeText(AddPlayers.this, "Please enter player name", Toast.LENGTH_SHORT).show(); // Display error message if names are empty.
                }
                // If player names are not empty.
                else {
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class); // Create intent to start MainActivity.
                    intent.putExtra("playerOne", getPlayerOneName); // Put player one name as extra data in intent.
                    intent.putExtra("playerTwo", getPlayerTwoName); // Put player two name as extra data in intent.
                    startActivity(intent); // Start MainActivity with intent.
                }
            }
        });

        // Set click listener for help button.
        helpButton.setOnClickListener(new View.OnClickListener() {
            // Method called when help button is clicked.
            @Override
            public void onClick(View view) {
                showInstructionsDialog(); // Call the method to show instructions dialog.
            }
        });
    }

    // Method to display instructions in a dialog.
    private void showInstructionsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // Create the builder for the dialog.
        builder.setTitle("Instructions"); // Set the title for the dialog.
        // Set the message for the dialog.
        builder.setMessage("Welcome to Tic Tac Toe! Let's get started by entering the names of the players in the fields below. Once you've done that, simply hit the 'Start Game' button and let the fun begin! \n Achieve three marks  (either X or O) in a row horizontally, vertically, or diagonally to win the game.\n\n\nHope you enjoy this game \n Happy Playing !!!\n\n\nA Game Developed by:- \nRamsha Ansari");
        // Set the OK button for the dialog.
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            // Method called when OK button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog when OK button is clicked.
            }
        });
        builder.create().show(); // Show the dialog.

    }

    // Override onBackPressed method to show exit confirmation dialog.
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // Create the builder for the dialog.
        builder.setTitle("Close Application"); // Set the title for the dialog.
        builder.setMessage("Dear User, Do you wish to close the application?"); // Set the message for the dialog asking the user if they want to close the application.
        // Set the action for the "Yes" button.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            // Method called when the "Yes" button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // Close the application.
            }
        });
        // Set the action for the "No" button.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            // Method called when the "No" button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss(); // Dismiss the dialog.
            }
        });
        builder.create().show(); // Show the dialog with the "Yes" and "No" buttons.
    }
}
// End of Program