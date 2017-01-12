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
        System.out.println(position.longitude + " / " + position.latitude + " ----> " + name);
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
}
