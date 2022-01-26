import SwiftUI
import shared

struct MovieDetailsScreenView: View {
    @EnvironmentObject var movieId: MovieID

    var body: some View {
        Text("Hello")
    }
}

extension MovieDetailsScreenView {
    class ViewModel: ObservableObject {
        private var actionManager: MovieDetailsUserActionManager

        @Published var state: MovieDetailsViewState = .loading

        init(movieId: Int32, actionManager: MovieDetailsUserActionManager) {
            self.actionManager = actionManager

            observeState()
            self.actionManager.action(event: MovieDetailsUserAction.LoadPage(movieId: movieId, imageWidth: 500, actorImageWidth: 300))
        }

        private func observeState() {
            actionManager.collectState(state: { state in
                self.state = MovieDetailsViewState(state)
            })
        }
    }
}