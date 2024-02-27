/*Main Activity Class for the game */

// Declaring the package.
package com.ramshaansari.tictactoe;

// Importing the required libraries.
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity; // Creates an activity in an Android application.
import android.content.DialogInterface;
import android.os.Bundle; // Handles activity state.
import android.view.View; // Handles UI elements.
import android.widget.ImageView; // Handles image views for displaying images.
import com.ramshaansari.tictactoe.databinding.ActivityMainBinding; // Import generated binding class for accessing views.
import java.util.ArrayList; // Handle lists i.e dynamic arrays.
import java.util.List; // List interface for lists or dynamic arrays.

// MainActivity class declaration which extends AppCompatActivity, indicating that it's an activity in an Android application.
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding; // Variable for holding reference to the binding object.
    private final List<int[]> combinationList = new ArrayList<>(); // List to store winning combinations.
    private int[] boxPositions = {0,0,0,0,0,0,0,0,0}; // Array to store positions of the boxes.
    private int playerTurn = 1; // Variable to store the current player's turn.
    private int totalSelectedBoxes = 1; // Variable to store the total number of boxes selected.

    // onCreate method to set the content view and handle button clicks.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // Call the superclass's onCreate method.
        binding = ActivityMainBinding.inflate(getLayoutInflater()); // Initialize the binding object.
        setContentView(binding.getRoot()); // Set the layout for the activity using binding.

        // Add winning combinations to the list.
        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});

        String getPlayerOneName = getIntent().getStringExtra("playerOne"); // Get player one (X) name from intent.
        String getPlayerTwoName = getIntent().getStringExtra("playerTwo"); // Get player two (O) name from intent.

        binding.playerOneName.setText(getPlayerOneName); // Set player one (X) name in TextView.
        binding.playerTwoName.setText(getPlayerTwoName); // Set player two (O) name in TextView.

        // Setting the click listener for each box.
        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(0)){
                    performAction((ImageView) view, 0);
                }
            }
        });
        binding.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(1)){
                    performAction((ImageView) view, 1);
                }
            }
        });
        binding.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(2)){
                    performAction((ImageView) view, 2);
                }
            }
        });
        binding.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(3)){
                    performAction((ImageView) view, 3);
                }
            }
        });
        binding.image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(4)){
                    performAction((ImageView) view, 4);
                }
            }
        });
        binding.image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(5)){
                    performAction((ImageView) view, 5);
                }
            }
        });
        binding.image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(6)){
                    performAction((ImageView) view, 6);
                }
            }
        });
        binding.image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(7)){
                    performAction((ImageView) view, 7);
                }
            }
        });
        binding.image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBoxSelectable(8)){
                    performAction((ImageView) view, 8);
                }
            }
        });

    }

    // Method to perform action on box click based on the player's turn.
    private void performAction(ImageView  imageView, int selectedBoxPosition) {
        boxPositions[selectedBoxPosition] = playerTurn; // Update box position array with player's turn.

        // Perform action based on the player's turn.
        if (playerTurn == 1) { // Check if it's player one's ((X) turn.
            imageView.setImageResource(R.drawable.ximage); // Set X image on the selected box.
            // Check if player one has won.
            if (checkResults()) {
                // If player one has won, create a result dialog showing player one as the winner.
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerOneName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false); // Disable the back button.
                resultDialog.show(); // Show the result dialog.
            }
            // Check if the match is a draw.
            else if(totalSelectedBoxes == 9) {
                // If all boxes are selected and there's no winner, create a result dialog showing the match as a draw.
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false); // Disable the back button.
                resultDialog.show(); // Show the result dialog.
            }
            else {
                changePlayerTurn(2); // Change player's turn to player two (O).
                totalSelectedBoxes++; // Increment the count of total selected boxes.
            }
        }
        // Check if it's player two's (O) turn.
        else {
            imageView.setImageResource(R.drawable.oimage); // Set O image on the selected box.
            // Check if player two has won.
            if (checkResults()) {
                // If player two has won, create a result dialog showing player two as the winner.
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, binding.playerTwoName.getText().toString()
                        + " is a Winner!", MainActivity.this);
                resultDialog.setCancelable(false); // Disable the back button.
                resultDialog.show(); // Show the result dialog.
            }
            // Check if the match is a draw.
            else if(totalSelectedBoxes == 9) {
                // If all boxes are selected and there's no winner, create a result dialog showing the match as a draw.
                ResultDialog resultDialog = new ResultDialog(MainActivity.this, "Match Draw", MainActivity.this);
                resultDialog.setCancelable(false); // Disable the back button.
                resultDialog.show(); // Show the result dialog.
            }
            else {
                changePlayerTurn(1); // Change player's turn to player one (X).
                totalSelectedBoxes++; // Increment the count of total selected boxes.
            }
        }
    }

    // Method to change the player's turn.
    private void changePlayerTurn(int currentPlayerTurn) {
        playerTurn = currentPlayerTurn; // Set the current player's turn.
        // If it's player one's (X) turn.
        if (playerTurn == 1) {
            binding.playerOneLayout.setBackgroundResource(R.drawable.black_border); // Highlight player one's (X) box.
            binding.playerTwoLayout.setBackgroundResource(R.drawable.white_box); // Unhighlight player two's (O) box.
        }
        // If it's player two's (O) turn.
        else {
            binding.playerTwoLayout.setBackgroundResource(R.drawable.black_border); // Highlight player two's (O) box.
            binding.playerOneLayout.setBackgroundResource(R.drawable.white_box); // Unhighlight player one's (X) box.
        }
    }

    // Method to check if player has won.
    private boolean checkResults(){
        boolean response = false; // Initialize the response as false i.e noone won.
        // Iterate over each winning combination.
        for (int i = 0; i < combinationList.size(); i++){
            final int[] combination = combinationList.get(i); // Get the current combination.

            // Check if the current player has filled all the boxes in any winning combination.
            if (boxPositions[combination[0]] == playerTurn && boxPositions[combination[1]] == playerTurn &&
                    boxPositions[combination[2]] == playerTurn) {
                response = true; // Set the response to true if player has won.
            }
        }
        return response; // Return the response indicating if player has won.
    }

    // Method to check if a box is selectable.
    private boolean isBoxSelectable(int boxPosition) {
        boolean response = false; // Initialize the response as false.
        // Check if the box is empty.
        if (boxPositions[boxPosition] == 0) {
            response = true; // Set the response to true if the box is selectable.
        }
        return response; // Return the response indicating if the box is selectable.
    }

    // Override onBackPressed method to show exit confirmation dialog.
    @Override
    public void onBackPressed() {
        showExitConfirmationDialog(); // Call the method to show exit confirmation dialog.
    }

    // Method to show exit confirmation dialog.
    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // Create the builder for the dialog.
        builder.setMessage("Are you sure you want to exit the game?"); // Set the message for the dialog.
        builder.setCancelable(false); // Make the dialog non-cancelable to force the user to make a choice.
        // Set action for the "Yes" button.
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            // Method called when the "Yes" button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish(); // Close the activity and exit the game.
            }
        });
        // Set action for the "No" button.
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            // Method called when the "No" button is clicked.
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel(); // Dismiss the dialog and resume the game.
            }
        });
        AlertDialog dialog = builder.create(); // Create the AlertDialog object.
        dialog.show(); // Show the dialog.
    }
    // Method to restart the match.
    public void restartMatch(){
        boxPositions = new int[] {0,0,0,0,0,0,0,0,0}; // Reset the box positions to empty.
        playerTurn = 1; // Reset the player's turn to player one (X).
        totalSelectedBoxes = 1; // Reset the count of total selected boxes to 1.

        // Reset the images of all boxes to empty.
        binding.image1.setImageResource(R.drawable.white_box);
        binding.image2.setImageResource(R.drawable.white_box);
        binding.image3.setImageResource(R.drawable.white_box);
        binding.image4.setImageResource(R.drawable.white_box);
        binding.image5.setImageResource(R.drawable.white_box);
        binding.image6.setImageResource(R.drawable.white_box);
        binding.image7.setImageResource(R.drawable.white_box);
        binding.image8.setImageResource(R.drawable.white_box);
        binding.image9.setImageResource(R.drawable.white_box);
    }
}
// End of Program