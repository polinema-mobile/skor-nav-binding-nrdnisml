package id.ac.polinema.skor.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import id.ac.polinema.skor.R;
import id.ac.polinema.skor.databinding.FragmentGoalBinding;
import id.ac.polinema.skor.models.GoalScorer;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoalFragment extends Fragment {

	private String requestKey;
	private GoalScorer goalScorer;
	EditText name,minutes;

	public GoalFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.goalScorer = new GoalScorer();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		FragmentGoalBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_goal, container, false);
		binding.setFragment(this);
		binding.setGoalScorer(goalScorer);
		name = binding.inputName;
		minutes = binding.inputMinute;
		requestKey = GoalFragmentArgs.fromBundle(getArguments()).getRequestKey();
		return binding.getRoot();
	}

	public void onSaveClicked(View view) {
		Bundle bundle = new Bundle();
		goalScorer.setName(name.getText().toString());
		goalScorer.setMinute(Integer.valueOf(minutes.getText().toString()));
		bundle.putParcelable(ScoreFragment.SCORER_KEY,goalScorer);
		getParentFragmentManager().setFragmentResult(requestKey,bundle);
		Navigation.findNavController(view).navigateUp();
	}

	public void onCancelClicked(View view) {
		Navigation.findNavController(view).navigateUp();
	}
}