import shared

enum FavoritesViewState {
    case ready(movies: [Movie])
    case empty
    case error
    case loading

    init(_ obj: FavoritesUIState?) {
        if obj is FavoritesUIState.Loading || obj == nil {
            self = .loading
        } else if let obj = obj as? FavoritesUIState.Ready {
            self = .ready(movies: obj.movies)
        } else if obj is FavoritesUIState.Error {
            self = .error
        } else if obj is FavoritesUIState.Empty {
            self = .empty
        } else {
            fatalError("Invalid state for FavoritesUIState")
        }
    }
}