package gs.goodservice.animationtest;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends Activity {

    ArrayList<String> values = new ArrayList<>();
    ListView lv;
    EditText messageText;
    Button button;
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView)findViewById(R.id.messagesContainer);
        messageText = (EditText)findViewById(R.id.messageEdit);
        button = (Button)findViewById(R.id.chatSendButton);

        adapter = new ChatAdapter(this, values);
        lv.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = messageText.getText().toString();
                if(!TextUtils.isEmpty(str)) {
                    values.add(str);
                    adapter.notifyDataSetChanged();
                    messageText.setText("");
                }
            }
        });

    }
}
