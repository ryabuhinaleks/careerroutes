import com.example.posts.features.list.domain.PostInteractor
import com.example.posts.features.list.domain.model.Post
import com.example.posts.features.list.presentation.EventState.Notification
import com.example.posts.features.list.presentation.PostListState
import com.example.posts.features.list.presentation.PostListViewModel
import com.example.testing.BaseCoroutineTest
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.just
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.koin.core.context.stopKoin
import kotlin.test.Test
import kotlin.test.assertEquals

class UserListViewModelTest : BaseCoroutineTest() {

    private val userId = 1
    private val postInteractor = mockk<PostInteractor>()

    override fun onSetUp() {}

    override fun onTearDown() {
        stopKoin()
        confirmVerified(postInteractor)
    }

    @Test
    fun `успешно выполнена загрузка`() = runTest {
        // precondition
        val post1 = Post(id = 1, title = "title", description = "description")
        val post2 = Post(id = 2, title = "title", description = "description")

        val allPosts = listOf(post1, post2)
        val favoritePosts = listOf(post1)

        coEvery { postInteractor.getPosts(userId) } returns flowOf(allPosts)
        coEvery { postInteractor.getFavorite(userId) } returns flowOf(favoritePosts)

        //action
        val viewModel = PostListViewModel(userId, postInteractor)
        viewModel.load()

        //result
        testScheduler.advanceUntilIdle()

        val finalState = viewModel.state.value
        assertTrue(finalState is PostListState.Content)

        val posts = (finalState as PostListState.Content).posts
        assertTrue(posts[0].isFavorite)
        assertTrue(!posts[1].isFavorite)
        coVerify { postInteractor.getPosts(userId) }
        coVerify { postInteractor.getFavorite(userId) }
    }

    @Test
    fun `добавить в избранное`() = runTest {
        // precondition
        val collectedEvents = mutableListOf<Notification>()
        val post = Post(id = 1, title = "title", description = "description")
        coEvery { postInteractor.addFavorite(post, userId) } just Runs

        // action
        val viewModel = PostListViewModel(userId, postInteractor)
        val collectJob = launch {
            viewModel.event.collect { collectedEvents.add(it) }
        }
        delay(1)
        viewModel.addFavorite(post)

        // result
        testScheduler.advanceUntilIdle()
        collectJob.cancel()

        coVerify { postInteractor.addFavorite(post, userId) }
        assertEquals(Notification(true), collectedEvents[0])
    }


    @Test
    fun `удалить из избранного`() = runTest {
        // precondition
        val collectedEvents = mutableListOf<Notification>()
        val post = Post(id = 1, title = "title", description = "description")
        coEvery { postInteractor.deleteFavorite(post, userId) } just Runs

        // action
        val viewModel = PostListViewModel(userId, postInteractor)
        val collectJob = launch {
            viewModel.event.collect { collectedEvents.add(it) }
        }
        delay(1)
        viewModel.deleteFavorite(post)

        // result
        testScheduler.advanceUntilIdle()
        collectJob.cancel()

        coVerify { postInteractor.deleteFavorite(post, userId) }
        assertEquals(Notification(false), collectedEvents[0])
    }
}
