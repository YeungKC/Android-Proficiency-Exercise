package yeungkc.com.gankio_for_android_proficiency_exercise.ui.fragment

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import yeungkc.com.gankio_for_android_proficiency_exercise.R
import yeungkc.com.gankio_for_android_proficiency_exercise.contract.GankPresenter
import yeungkc.com.gankio_for_android_proficiency_exercise.contract.GankView
import yeungkc.com.gankio_for_android_proficiency_exercise.databinding.FragmentGankBinding
import yeungkc.com.gankio_for_android_proficiency_exercise.extensions.setOnLoadMore
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.AutoBean
import yeungkc.com.gankio_for_android_proficiency_exercise.model.bean.GankResult
import yeungkc.com.gankio_for_android_proficiency_exercise.ui.adapter.GankAdapter

class GankFragment : BasePageFragment(), GankView {
    companion object {

        const val CATEGORICAL = "CATEGORICAL"
        fun newInstance(categorical: String): GankFragment {
            val args = Bundle()
            args.putString(CATEGORICAL, categorical)
            val fragment = GankFragment()
            fragment.arguments = args
            return fragment
        }
    }

    val binding: FragmentGankBinding by lazy { FragmentGankBinding.inflate(LayoutInflater.from(context)) }

    private lateinit var categorical: String
    val gankAdapter by lazy { GankAdapter() }
    private lateinit var presenter: GankPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        categorical = arguments.getString(CATEGORICAL)
        presenter = GankPresenter(categorical)
        presenter.bind(this)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        initView()
        initEvent()
        return binding.root
    }

    fun initView() {
        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
        binding.recyclerView.layoutManager = LinearLayoutManager(context).apply { recycleChildrenOnDetach = true }
        binding.recyclerView.adapter = gankAdapter
    }

    fun initEvent() {
        binding.refreshLayout.setOnRefreshListener {
            if (!gankAdapter.isCanLoading()) {
                binding.refreshLayout.isRefreshing = false
                return@setOnRefreshListener
            }
            presenter.getRemoteContent()
        }

        gankAdapter.onClickErrorItemListener = { presenter.getRemoteContent(requestPage) }

        binding.recyclerView.setOnLoadMore {
            if (presenter.isRemoteLoading()) return@setOnLoadMore
            presenter.getRemoteContent(requestPage + 1)
        }
    }

    override fun initData() {
        presenter.getRemoteContent()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unBind(isDetached)
    }

    override fun onLoading() {
        gankAdapter.onLoading(requestPage,
                onStartRefresh = { binding.refreshLayout.isRefreshing = true }
        )
    }

    override fun onError(error: Throwable) {
        gankAdapter.onLoadError(
                requestPage,
                onStopRefresh = { binding.refreshLayout.isRefreshing = false },
                onRefreshError = {
                    Snackbar.make(binding.recyclerView, R.string.connection_fail, Snackbar.LENGTH_LONG)
                            .setAction(R.string.retry) {
                                presenter.getRemoteContent(requestPage)
                            }
                            .show()
                })
    }

    override fun setData(data: List<GankResult>) {
        val list: List<AutoBean>

        if (currentPage > 0) {
            list = gankAdapter.dataSets + data
        } else {
            list = data
        }

        gankAdapter.replaceWith(list) {
            binding.refreshLayout.isRefreshing = false

            if (gankAdapter.isHaveDataSets()) {
                if (isNoData) {
                    gankAdapter.showLoadMoreNoData()
                } else {
                    gankAdapter.showLoadingMore()
                }
            }
        }
    }

    override var requestPage: Int = 0
    override var currentPage: Int = -1
    override var isNoData: Boolean = false
}