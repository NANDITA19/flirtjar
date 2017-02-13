package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.flirtjar.R;

import adapters.ChatListAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import models.ChatUser;

/**
 * Created by rutvik on 2/5/2017 at 4:13 PM.
 */

public class FragmentChat extends Fragment
{

    @BindView(R.id.rv_chatList)
    RecyclerView rvChatList;

    @BindView(R.id.sv_searchChat)
    SearchView svSearchChat;

    ChatListAdapter adapter;

    public static FragmentChat newInstance()
    {
        return new FragmentChat();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        ButterKnife.bind(this, view);

        rvChatList.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvChatList.setHasFixedSize(true);

        adapter = new ChatListAdapter(getActivity());

        rvChatList.setAdapter(adapter);

        addDummyData();

        return view;
    }

    private void addDummyData()
    {
        adapter.addChatUser(new ChatUser("Nidhi"));
    }
}
