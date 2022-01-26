import shared

enum MoviesViewState {
    case ready(comingSoonMovies: [Movie],
               movieGenres: [Genre],
               trendingNowMovies: [Movie])
    case loading
    case error
    case firstIn

    init(_ obj: MoviesUIState?) {
        if obj is MoviesUIState.Loading || obj == nil {
            self = .loading
        } else if let obj = obj as? MoviesUIState.Ready {
            self = .ready(comingSoonMovies: obj.comingSoonMovies,
                    movieGenres: obj.movieGenres,
                    trendingNowMovies: obj.trendingNowMovies)
        } else if obj is MoviesUIState.Error {
            self = .error
        } else if obj is MoviesUIState.FirstIn {
            self = .firstIn
        } else {
            fatalError("Invalid state for MoviesUIState")
        }
    }
}
