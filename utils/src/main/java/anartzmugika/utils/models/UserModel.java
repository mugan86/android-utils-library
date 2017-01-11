package anartzmugika.utils.models;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import anartzmugika.utils.Constants;
import anartzmugika.utils.R;

/***************************************************************************************************
 * Created by anartzmugika on 25/11/16. Updated by Anartz Mugika on 2017-01-11
 * UserModel use Google account basic data, if extends and reuse functions, extends in new object ;)
 **************************************************************************************************/

public class UserModel {

    private String id;
    private String name;
    private String email;
    private String photo_url;

    public UserModel(){}
    public UserModel(String id, String name, String email, String photo_url)
    {
        setId(id);
        setName(name);
        setEmail(email);
        setPhoto_url(photo_url);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*********
     * Take default photo from constants
     * @return Photo
     */
    public String getPhoto_url() {
        if (photo_url == null || photo_url.equals("")) return Constants.DEFAULT_PHOTO_URL;
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    /***********************************************************************************************
     * Use in Activity with NavigationView and customheader
     * @param ouser User object
     * @param context Application Context
     * @param profile_photo User profile photo url
     * @param name User Name and Lastname
     * @param email User email
     ***********************************************************************************************/
    public static void setUserDataInMenuHeader(UserModel ouser, Context context, ImageView profile_photo,
                                               TextView name, TextView email)
    {
        if (ouser.getPhoto_url() != null)
        {
            Picasso.with(context).load(ouser.getPhoto_url()).
                    into(profile_photo);
        }
        else
        {
            Picasso.with(context).load(R.drawable.android).
                    into(profile_photo);
        }


        name.setText(ouser.getName());

        email.setText(ouser.getEmail());

        //if (Actions.CheckInternetConnection(context)) registerUserDatainDB(context, ouser.getId(), ouser.getEmail(), ouser.getName(), ouser.getPhoto_url());
    }
}
