package com.example.draw002;

import android.os.Bundle;
import android.os.Environment;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Path;
import android.graphics.Rect;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

@SuppressLint("DrawAllocation")
public class MainActivity extends Activity {

	private FrameLayout frame = null;
	private Paint paint = null;
	private Path path = null;
	private ImageView imageView = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imageView = (ImageView)findViewById(R.id.imageView1);
		frame = (FrameLayout)findViewById(R.id.fram);
		frame.addView(new myView(this));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressLint("DrawAllocation")
	private class myView extends View{

		public myView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@SuppressLint("DrawAllocation")
		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			paint = new Paint();
			//设置使用抗锯齿
			paint.setAntiAlias(true);
			//设置画笔颜色
			paint.setColor(0xffff6600);
			paint.setTextSize(18);
			//设置填充方式为描边
			paint.setStyle(Style.STROKE);
			
			/**
			 * 绘制圆形路径
			 * **/
			path = new Path();
			//添加逆时针的圆形路径
			path.addCircle(70, 70, 40, Path.Direction.CCW);
			canvas.drawPath(path, paint);
			/**
			 * 绘制折线
			 * **/
			path = new Path();
			path.moveTo(150, 100);
			path.lineTo(200, 45);
			path.lineTo(100, 222);
			path.lineTo(10, 100);
			canvas.drawPath(path, paint);
			/**
			 * 绘制三角
			 * **/
			path = new Path();
			path.moveTo(350, 80);
			path.lineTo(400, 30);
			path.lineTo(450, 80);
			path.close();
			canvas.drawPath(path, paint);
			/**
			 * 绘制绕路径的环形文字
			 * **/
			String str = "宁取余生求富贵，不留残年独彷徨.";
			path = new Path();
			//添加逆时针的圆形路径
			path.addCircle(200,180, 45, Path.Direction.CW);
			paint.setStyle(Style.FILL);
			canvas.drawTextOnPath(str, path, 0, -18, paint);
			
			String url = Environment.getExternalStorageDirectory().getPath()+"/mms/xxx.jpg";
			Bitmap bm = BitmapFactory.decodeFile(url);
			canvas.drawBitmap(bm, 0,30, paint);
			Rect r = new Rect(95,150,175,240);
			Rect t = new Rect(320,30,400,120);
			canvas.drawBitmap(bm, r,t, paint);
			/**
			 * 此处有问题，暂未解决
			 * **/
//			//使用颜色组创建一个对象
//			Bitmap bi = Bitmap.createBitmap(new int[]{Color.RED,Color.GREEN,Color.BLUE,Color.MAGENTA},4,1,Config.RGB_565);
//			imageView.setImageBitmap(bi);
			super.onDraw(canvas);
		}
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		BitmapDrawable b = (BitmapDrawable)imageView.getDrawable();
		if (b != null && !b.getBitmap().isRecycled()) {
			b.getBitmap().recycle();
		}
		super.onDestroy();
	}
}
