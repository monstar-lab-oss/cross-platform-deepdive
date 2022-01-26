import shared

enum MovieDetailsViewState {
    case ready(
            movieDetails: MovieDetails,
            casting: [Actor],
            isMovieFavorite: Bool
    )
    case error
    case loading

    init(_ obj: MovieDetailsUIState?) {
        if let obj = obj as? MovieDetailsUIState.Ready {
            self = .ready(movieDetails: obj.movieDetails, casting: obj.casting, isMovieFavorite: obj.isMovieFavorite)
        } else if obj is MovieDetailsUIState.Error {
            self = .error
        } else if obj is MovieDetailsUIState.Loading {
            self = .loading
        } else {
            fatalError("Invalid state for MovieDetailsUIState")
        }
    }
}