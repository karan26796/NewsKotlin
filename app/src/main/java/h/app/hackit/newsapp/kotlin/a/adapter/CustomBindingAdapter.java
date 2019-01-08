package h.app.hackit.newsapp.kotlin.a.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import org.joda.time.DateTime;
import org.joda.time.Minutes;

import java.text.SimpleDateFormat;
import java.util.Objects;

import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import h.app.hackit.newsapp.R;
import h.app.hackit.newsapp.kotlin.a.utils.TextDrawable;

/**
 * Created by phanirajabhandari on 7/8/15.
 */
public class CustomBindingAdapter {

    /*@BindingAdapter("bind:onClick")*/

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url)
                .into(imageView);
    }

    @BindingAdapter("bind:timeElaspsed")
    public static void calculateTimePassed(TextView textView, String date) {
        final DateTime givenTime = new DateTime(date);
        final DateTime currentTime = new DateTime();
        final SimpleDateFormat formatter = new SimpleDateFormat("MMM d"); // i.e. Feb 7
        String time;
        final int minutes = Minutes.minutesBetween(givenTime, currentTime).getMinutes();

        // If minutes are bigger than 60 then use hours
        if (minutes > 60) {
            // Convert to hours
            final int hours = minutes / 60;
            // If passed time is more than 24 hours then use days
            if (hours >= 24) {
                // Convert to days
                final int days = hours / 24;
                if (days > 7) {
                    // If passed time is bigger than 7 days then display date
                    time = formatter.format(givenTime.getMillis());
                } else {
                    // If days are smaller than 7 then return number of days
                    time = days + " days ago";
                }
            } else {
                time = hours + " hours ago";
            }
        } else {
            time = minutes + " minutes ago";
        }
        textView.setText(time);
    }

    @BindingAdapter("bind:avatar")
    public static void loadAvatar(ImageView imageView, FirebaseUser user) {
        TextDrawable.Builder builder = new TextDrawable.Builder();
        builder.textColor = ContextCompat.getColor(imageView.getContext(), R.color.colorAccent);
        builder.text = Objects.requireNonNull(user.getEmail()).substring(0, 1);
        Color.argb(85, 101, 31, 255);
        builder.color = Color.parseColor("#dac7ff");
        builder.buildRound(builder.text, builder.color);
        TextDrawable drawable = new TextDrawable(builder);
        imageView.setImageDrawable(drawable);
    }

    @BindingAdapter("bind:drawable")
    public static void loadDrawable(ImageView imageView, Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    @BindingAdapter("bind:visibilityOnIndex")
    public static void toggleVisibility(View view, int index) {
        view.setVisibility(index > 0 ? View.VISIBLE : View.GONE);
    }
}
