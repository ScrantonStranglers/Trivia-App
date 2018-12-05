package com.stranglers.scranton.triviaapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class Leaderboard extends AppCompatActivity {

    boolean fromMenu;

    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Intent intent = getIntent();
        ImageButton homeButton = (ImageButton) findViewById(R.id.homeButton5);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                home();
            }
        });
        tableLayout = (TableLayout) findViewById(R.id.leaderboard_table);
        constructTable();
    }

    private void home() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void constructTable() {
        TableRow row = new TableRow(getApplicationContext());

        TextView name = createHeaderCell("User Name");
        name.setTextColor(Color.WHITE);
        TextView cat = createHeaderCell("Category");
        cat.setTextColor(Color.WHITE);
        TextView score = createHeaderCell("Score");
        score.setTextColor(Color.WHITE);

        row.addView(name);
        row.addView(cat);
        row.addView(score);
        tableLayout.addView(row);
        final LeaderboardRepository leaderboardRepository = new LeaderboardRepository(getApplicationContext());
        new AsyncTask<Void, Void, List<LeaderboardEntry>>() {
            @Override
            protected List<LeaderboardEntry> doInBackground(Void... voids) {
                return leaderboardRepository.getAllEntries();
            }

            @Override
            protected void onPostExecute(List<LeaderboardEntry> entries) {
                for (LeaderboardEntry entry : entries) {

                    TableRow row = new TableRow(getApplicationContext());
                    TextView name = createCell(entry.getUsername());
                    name.setTextColor(Color.WHITE);
                    TextView cat = createCell(entry.getCategory());
                    cat.setTextColor(Color.WHITE);
                    TextView score = createCell(Integer.toString(entry.getScore()));
                    score.setTextColor(Color.WHITE);

                    row.addView(name);
                    row.addView(cat);
                    row.addView(score);
                    tableLayout.addView(row);
                }
            }
        }.execute();
    }

    private TextView createCell(String text) {
        TextView cell = new TextView(getApplicationContext());
        cell.setText(text);
        cell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        cell.setBackground(getResources().getDrawable(R.drawable.cell, getTheme()));
        return cell;
    }

    private TextView createHeaderCell(String text) {
        TextView cell = new TextView(getApplicationContext());
        cell.setText(text);
        cell.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        cell.setTypeface(null, Typeface.BOLD);
        cell.setBackground(getResources().getDrawable(R.drawable.cell, getTheme()));
        return cell;
    }
}
