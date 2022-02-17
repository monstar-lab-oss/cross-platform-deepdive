import Realm from "realm";

// Declare Schema
class MovieSchema extends Realm.Object {}
MovieSchema.schema = {
    name: 'Movie',
    properties: {
        title: 'string',
    }
};

// Create realm
let realm = new Realm({schema: [MovieSchema], schemaVersion: 1});

let getAllMovies = () => {
    return realm.objects('Movie');
}

let addMovie = (_title) => {
    realm.write(() => {
        const movie = realm.create('Movie', {
            title: _title,
        });
    });
}

let deleteAllMovies = () => {
    realm.write(() => {
        realm.delete(getAllMovies());
    })
}


// Export the realm
export default realm;

export {
    getAllMovies,
    addMovie,
    deleteAllMovies,
}
