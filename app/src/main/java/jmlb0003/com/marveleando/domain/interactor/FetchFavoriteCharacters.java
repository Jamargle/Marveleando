package jmlb0003.com.marveleando.domain.interactor;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;

public final class FetchFavoriteCharacters extends UseCase<Void, List<Character>> {

    public static final String NAME = "InjectionKey:FetchFavoriteCharactersUseCase";

    private final CharacterLocalRepository characterLocalRepository;

    @Inject
    public FetchFavoriteCharacters(
            final CharacterLocalRepository characterLocalRepository,
            final ThreadExecutor threadExecutor,
            final PostExecutionThread postExecutionThread) {

        super(threadExecutor, postExecutionThread);
        this.characterLocalRepository = characterLocalRepository;
    }

    @Override
    protected Observable<List<Character>> buildUseCaseObservable(@Nullable final Void params) {
        return Observable.create(new ObservableOnSubscribe<List<Character>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Character>> emitter) {
                emitter.onNext(characterLocalRepository.getFavoriteCharacters());
            }
        });
    }

}
