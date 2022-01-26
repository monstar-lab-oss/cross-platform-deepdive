import SwiftUI
import shared

struct MoviesScreenView: View {
    @ObservedObject var viewModel: MoviesScreenView.ViewModel

    var body: some View {
        NavigationView {
            switch viewModel.state {
            case .loading:
                LoadingContentView()
            case .error:
                ErrorView(message: "Movies")
            case .firstIn:
                Text("// TODO: First In")
            case .ready(let comingSoonMovies, let movieGenres, let trendingNowMovies):
                ContentReady(
                        comingSoonMovies: comingSoonMovies,
                        movieGenres: movieGenres,
                        trendingNowMovies: trendingNowMovies
                )
            }
        }
    }
}

private struct ContentReady: View {
    var comingSoonMovies: [Movie]
    var movieGenres: [Genre]
    var trendingNowMovies: [Movie]

    var body: some View {
        VStack {
            ComingSoonSection(comingSoonMovies)
            GenresSection(movieGenres)
            TrendingNowSection(trendingNowMovies)
        }
                .frame(alignment: .topLeading)
                .padding()
    }
}

private struct ComingSoonSection: View {
    var comingSoonMovies: [Movie]

    var body: some View {
        SectionView(title: "Coming Soon") {
            ScrollView(.horizontal) {
                HStack(spacing: 8) {
                    ForEach(comingSoonMovies, id: \.id) { movie in
                        ComingSoonCardView(movie: movie)
                    }
                }
            }
        }
    }

    init(_ comingSoonMovies: [Movie]) {
        self.comingSoonMovies = comingSoonMovies
    }
}

private struct GenresSection: View {
    var genres: [Genre]

    var body: some View {
        ScrollView(.horizontal) {
            HStack(spacing: 8) {
                ForEach(genres, id: \.id) { genre in
                    PillButton {
                        Text(genre.name)
                    }
                }
            }
        }
    }

    init(_ genres: [Genre]) {
        self.genres = genres
    }
}

private struct TrendingNowSection: View {
    var trendingNowMovies: [Movie]

    var body: some View {
        SectionView(title: "Trending Now") {
            ScrollView(.horizontal) {
                HStack(spacing: 8) {
                    ForEach(trendingNowMovies, id: \.id) { movie in
                        MovieCardView(movie: movie)
                    }
                }
            }
        }
    }

    init(_ trendingNowMovies: [Movie]) {
        self.trendingNowMovies = trendingNowMovies
    }
}

extension MoviesScreenView {
    class ViewModel: ObservableObject {
        private let actionManager: MoviesUserActionManager

        @Published var state: MoviesViewState = .loading

        init(actionManager: MoviesUserActionManager) {
            self.actionManager = actionManager

            observeState()
            self.actionManager.action(event: MoviesUserAction.LoadPage(comingSoonWidth: 500, trendingNowWidth: 500))
        }

        private func observeState() {
            actionManager.collectState(state: { state in
                self.state = MoviesViewState(state)
            })
        }
    }
}