package example.mpaani.com.mpaani;

import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import example.mpaani.com.mpaani.adapter.BaseRecyclerAdapter;
import example.mpaani.com.mpaani.models.Post;
import example.mpaani.com.mpaani.ui.MainActivity;
import example.mpaani.com.mpaani.ui.PostDetailActivity;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<PostDetailActivity> secondRule = new ActivityTestRule<PostDetailActivity>(PostDetailActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            InstrumentationRegistry.getTargetContext();
            Post post = new Post();
            post.setTitle("Testing Title");
            post.setBody("Testing Body");
            post.setId(9);
            post.setUserId(1);
            Bundle bundle = new Bundle();
            bundle.putSerializable("post", post);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.putExtra("Detail", bundle);
            return intent;
        }
    };

    @Test
    public void ensureListViewIsPresent() {
        MainActivity activity = rule.getActivity();
        View viewById = activity.findViewById(R.id.rvPostList);
        assertThat(viewById, notNullValue());
        assertThat(viewById, instanceOf(RecyclerView.class));
        RecyclerView rvView = (RecyclerView) viewById;
        RecyclerView.Adapter adapter = rvView.getAdapter();
        assertThat(adapter, instanceOf(BaseRecyclerAdapter.class));
        assertThat(adapter.getItemCount(), greaterThan(5));
        Log.e("Sandy", adapter.getItemCount() + "");
    }

    @Test
    public void ensurePostDetailScreenIsOpening() {
        PostDetailActivity activity = secondRule.getActivity();

        View postTitle = activity.findViewById(R.id.lblPostTitle);
        View postBody = activity.findViewById(R.id.lblPostBody);

        assertThat(postTitle, notNullValue());
        assertThat(postBody, notNullValue());
        assertThat(postTitle, instanceOf(TextView.class));
        assertThat(postBody, instanceOf(TextView.class));
        TextView txtPostTitle = (TextView) postTitle;
        TextView txtPostBody = (TextView) postBody;
        assertThat(txtPostTitle.getText().toString(), is("Testing Title"));
        assertThat(txtPostBody.getText().toString(), is("Testing Body"));
    }

    @Test
    public void ensureUserDetailIsLoaded() {
        PostDetailActivity activity = secondRule.getActivity();

        View userName = activity.findViewById(R.id.txtUserName);

        assertThat(userName, notNullValue());
        assertThat(userName, instanceOf(TextView.class));
        TextView txtUserName = (TextView) userName;
        assertThat(txtUserName.getText().toString(), is("Leanne Graham"));
    }

    @Test
    public void ensureCommentsCountIsLoaded() throws InterruptedException {
        PostDetailActivity activity = secondRule.getActivity();
        Thread.sleep(2000);
        View commentCount = activity.findViewById(R.id.txtCommentCount);

        assertThat(commentCount, notNullValue());
        assertThat(commentCount, instanceOf(TextView.class));
        TextView txtCommentCount = (TextView) commentCount;
        assertThat(txtCommentCount.getText().toString(), is("Total Comments : " + 5));
    }

}
