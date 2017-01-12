package anartzmugika.utils.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.ArrayList;
import java.util.Random;

import anartzmugika.utils.R;

/***************************************
 * Created by anartzmugika on 27/2/16.
 */
public class PersonModel implements ClusterItem, Parcelable {
    public String name;
    public int profilePhoto;
    private LatLng mPosition;
    private String photo_url;

    public PersonModel(LatLng position, String name, int pictureResource, String photo_url) {
        this.name = name;
        profilePhoto = pictureResource;
        mPosition = position;
        this.photo_url = photo_url;
    }

    public PersonModel(Parcel in) {
        readFromParcel(in);
    }
    public PersonModel() {
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    public String getName()
    {
        return this.name;
    }

    public void setmPosition(double lat, double lng)
    {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(profilePhoto);
        dest.writeParcelable(mPosition, flags);
    }

    public void readFromParcel(Parcel in)
    {
        name = in.readString();
        profilePhoto = in.readInt();
        mPosition = in.readParcelable(LatLng.class.getClassLoader());;
    }

    public static final Creator<PersonModel> CREATOR
            = new Creator<PersonModel>() {
        public PersonModel createFromParcel(Parcel in) {
            return new PersonModel(in);
        }

        public PersonModel[] newArray(int size) {
            return new PersonModel[size];
        }
    };

    public ArrayList<PersonModel> getItems() {

        ArrayList <PersonModel> person_list = new ArrayList<>();
        person_list.add(new PersonModel(position(), "Walter", R.drawable.android, "http://static.panoramio.com/photos/original/101779498.jpg"));
        person_list.add(new PersonModel(position(), "Gran", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/14449320.jpg"));
        person_list.add(new PersonModel(position(), "Ruth", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/27049250.jpg"));
        person_list.add(new PersonModel(position(), "Stefan", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/20450379.jpg"));
        person_list.add(new PersonModel(position(), "Mechanic", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/8636065.jpg"));
        person_list.add(new PersonModel(position(), "Yeats", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/8636188.jpg"));
        person_list.add(new PersonModel(position(), "John", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/90076639.jpg"));
        person_list.add(new PersonModel(position(), "Trevor the Turtle", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/89815173.jpg"));
        person_list.add(new PersonModel(position(), "Teach", R.drawable.android, "http://mw2.google.com/mw-panoramio/photos/medium/90080431.jpg"));
        return person_list;
    }

    private LatLng position() {
        return new LatLng(random(51.6723432, 51.38494009999999), random(0.148271, -0.3514683));
    }

    private double random(double min, double max) {
        return new Random(1984).nextDouble() * (max - min) + min;
    }
}
