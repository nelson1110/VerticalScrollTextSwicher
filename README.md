# VerticalScrollTextSwicher
It's a vertical scroll textview,for android.

first write in xml:

<com.example.View.VerticalScrollTextSwicher
            android:id="@+id/broadcast"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:background="@drawable/text_bg"
            />

second write in java:

test = (VerticalScrollTextSwicher) findViewById(R.id.broadcast);
        List<String> list = new ArrayList<>();
        list.add("test1");
        list.add("test2");
        list.add("test3");
        list.add("test4");
        
	test.setTexts(list);
        test.setContentTextColor(Color.parseColor("#FFff0000"));
        test.start();

you also can use test.setContentTextSize() to set the text's size,and setTextDuration() to set the time you want to delay between two strings.and the same to SetAnimDuration().