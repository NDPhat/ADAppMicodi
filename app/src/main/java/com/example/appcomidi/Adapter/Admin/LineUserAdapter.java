package com.example.appcomidi.Adapter.Admin;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.appcomidi.Model.Users;
import com.example.appcomidi.R;
import com.example.appcomidi.ViewModel.AccountViewModel;
import com.example.appcomidi.ViewModel.CartViewModel;
import com.example.appcomidi.ViewModel.UserViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LineUserAdapter extends BaseAdapter implements Filterable {
    List<Users> usersListold;
    List<Users> musersList;
    Context context;
    IFChitietClick ifChitietClick;

    public LineUserAdapter(List<Users> usersList, Context context,IFChitietClick ifChitietClick) {
        this.musersList = usersList;
        this.context = context;
        this.ifChitietClick=ifChitietClick;
        this.usersListold=usersList;
    }

    @Override
    public Filter getFilter()
    {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
               String search=charSequence.toString();
               if (search.isEmpty())
               {
                   musersList=usersListold;
               }
               else
               {
                   List<Users> TusersList=new ArrayList<>();
                   for (Users users:musersList)
                   {
                       if (users.getName().toLowerCase().contains(search.toLowerCase()))
                       {
                            TusersList.add(users);
                       }
                   }
                   musersList=TusersList;
               }
               FilterResults filterResults=new FilterResults();
               filterResults.values=musersList;
               return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                musersList= (List<Users>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public interface IFChitietClick
    {
        void IFButtonChitietClick(int uid);
    }

    public class ViewHolder
    {
        TextView ten,id;
        ImageButton btnchitiet,btndelete;
        ImageView ava;
    }
    @Override
    public int getCount() {
        if (musersList!=null)
        {
            return musersList.size();
        }

        return 0;
    }

    @Override
    public Object getItem(int i) {
        return musersList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder=null;
        if (view==null)
        {
            viewHolder=new ViewHolder();
            LayoutInflater layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.lineuseradmin,null);
            viewHolder.ten=view.findViewById(R.id.AtenUS);
            viewHolder.id=view.findViewById(R.id.AIdUS);
            viewHolder.ava=view.findViewById(R.id.AphotoUS);
            viewHolder.btnchitiet=view.findViewById(R.id.AbtnChitiet);
            viewHolder.btndelete=view.findViewById(R.id.AbtnDelete);
            view.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) view.getTag();
        }
        final  Users users= (Users) getItem(position);
        if (AccountViewModel.getDataAccountbyAccid(users.getAccid()).getActive().equals("Banned"))
        {
            viewHolder.ten.setText("Banned");
            viewHolder.btndelete.setEnabled(false);
            viewHolder.btnchitiet.setEnabled(false);


        }
        else {
            viewHolder.ten.setText(users.getName());
        }
        viewHolder.id.setText(""+(position+1));

        if (users.getImage()==null)
        {
            viewHolder.ava.setImageResource(R.drawable.avamacdinh);
        }
        else {
            Bitmap bitmap=BitmapFactory.decodeByteArray(users.getImage(),0, users.getImage().length);
            viewHolder.ava.setImageBitmap(bitmap);
        }
        viewHolder.btnchitiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ifChitietClick.IFButtonChitietClick(users.getId());
            }
        });
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Xác nhận khóa tài khoản này!!");
                builder.setMessage("Bạn có chắc muốn khóa tài khoản này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        UserViewModel.updateStatusUserById(users.getAccid());
                        finalViewHolder.ten.setText("Banned");
                        notifyDataSetChanged();
                        Toast.makeText(context,"Khóa tài khoản thành công !!",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        notifyDataSetChanged();
                    }
                });
                builder.show();

            }
        });

        return view;
    }
}
