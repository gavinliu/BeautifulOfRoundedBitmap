package cn.gavinliu.demo.beautifulofroundedbitmap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int imageSize, radius;

    ImageView imageView;
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);
        imageView2 = (ImageView) findViewById(R.id.image2);

        imageSize = getResources().getDimensionPixelSize(R.dimen.image_size);
        radius = getResources().getDimensionPixelSize(R.dimen.radius);

        new LoadTask1().execute();
        new LoadTask2().execute();
    }

    class LoadTask1 extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap result = Bitmap.createBitmap(imageSize, imageSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.an);

            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(0, 0, imageSize, imageSize);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);

            paint.setXfermode(null);
            canvas.drawRoundRect(rectF, radius, radius, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            canvas.drawBitmap(bitmap, rect, rectF, paint);
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

    class LoadTask2 extends AsyncTask<Void, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(Void... params) {
            Bitmap result = Bitmap.createBitmap(imageSize, imageSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.an);

            final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
            final RectF rectF = new RectF(0, 0, imageSize, imageSize);

            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setFilterBitmap(true);


            Path path = new Path();
            path.addRoundRect(rectF, radius, radius, Path.Direction.CW);
            canvas.clipPath(path, Region.Op.INTERSECT);

            canvas.drawBitmap(bitmap, rect, rectF, paint);

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView2.setImageBitmap(bitmap);
        }
    }

}
