package com.bushra.programmingquize;

import android.os.Parcel;
import android.os.Parcelable;

public class QuestionModel implements Parcelable

{

    private int mTextResId;
    private boolean mAnswerTrue;
    private int type;

    public QuestionModel(int mTextResId, boolean mAnswerTrue, int type) {
        this.mTextResId = mTextResId;
        this.mAnswerTrue = mAnswerTrue;
        this.type = type;
    }

    protected QuestionModel(Parcel in) {
        mTextResId = in.readInt();
        mAnswerTrue = in.readByte() != 0;
        type = in.readInt();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    public int getmTextResId() {
        return mTextResId;
    }

    public void setmTextResId(int mTextResId) {
        this.mTextResId = mTextResId;
    }

    public boolean ismAnswerTrue() {
        return mAnswerTrue;
    }

    public void setmAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mTextResId);
        parcel.writeByte((byte) (mAnswerTrue ? 1 : 0));
        parcel.writeInt(type);
    }
}
