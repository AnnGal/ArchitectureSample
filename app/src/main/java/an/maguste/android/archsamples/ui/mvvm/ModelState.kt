package an.maguste.android.archsamples.ui.mvvm

sealed class ModelState {
    class InitializeState: ModelState();
    class LoadingState: ModelState()
    class SendingState: ModelState()
    class LoginSucessState: ModelState()
    class LoadedState<T>(val data: List<T>): ModelState()
    class NoItemsState : ModelState()
    class ErrorState<T>(val message: T): ModelState()
}