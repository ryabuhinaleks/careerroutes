import com.example.testing.BaseCoroutineTest
import com.example.users.features.domain.UserInteractor
import com.example.users.features.domain.model.User
import com.example.users.features.presentation.UserListState
import com.example.users.features.presentation.UserListViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.koin.core.context.stopKoin
import java.io.IOException
import kotlin.test.Test
import kotlin.test.assertEquals

class UserListViewModelTest : BaseCoroutineTest() {

    private val userInteractor = mockk<UserInteractor>()

    override fun onSetUp() {}

    override fun onTearDown() {
        stopKoin()
        confirmVerified(userInteractor)
    }

    @Test
    fun `успешно выполнена загрузка`() = runTest {
        // precondition
        val users = listOf(User(id = 1, name = "name", email = "email", phone = "phone"))

        coEvery { userInteractor.getUsers() } coAnswers {
            delay(10)
            users
        }

        val allStates = mutableListOf<UserListState>()
        val viewModel = UserListViewModel(userInteractor)

        launch {
            viewModel.state.take(2).collect { state -> allStates.add(state) }
        }

        //result
        testScheduler.advanceUntilIdle()

        coVerify { userInteractor.getUsers() }
        assertTrue(allStates[0] is UserListState.Loading)
        assertTrue(allStates[1] is UserListState.Content)

        val contentState = allStates.find { it is UserListState.Content }
        assertEquals(users, (contentState as UserListState.Content).users)
    }

    @Test
    fun `ошибка при загрузке`() = runTest {
        // precondition
        val errorMessage = "errorMessage"
        coEvery { userInteractor.getUsers() } coAnswers {
            delay(10)
            throw IOException(errorMessage)
        }

        val allStates = mutableListOf<UserListState>()

        //action
        val viewModel = UserListViewModel(userInteractor)

        launch {
            viewModel.state.take(2).collect { state -> allStates.add(state) }
        }

        //result
        testScheduler.advanceUntilIdle()

        coVerify { userInteractor.getUsers() }
        assertTrue(allStates[0] is UserListState.Loading)
        assertTrue(allStates[1] is UserListState.Error)
    }
}
