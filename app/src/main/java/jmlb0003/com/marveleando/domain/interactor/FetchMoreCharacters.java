package jmlb0003.com.marveleando.domain.interactor;

import android.support.annotation.Nullable;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import jmlb0003.com.marveleando.domain.model.Character;
import jmlb0003.com.marveleando.domain.repository.CharacterLocalRepository;
import jmlb0003.com.marveleando.domain.repository.CharacterNetworkRepository;

public class FetchMoreCharacters extends UseCase<Integer, List<Character>> {

    public static final String NAME = "InjectionKey:FetchMoreCharactersUseCase";

    private final CharacterLocalRepository characterLocalRepository;
    private final CharacterNetworkRepository characterNetworkRepository;

    @Inject
    public FetchMoreCharacters(
            final CharacterLocalRepository characterLocalRepository,
            final CharacterNetworkRepository characterNetworkRepository,
            final ThreadExecutor threadExecutor,
            final PostExecutionThread postExecutionThread) {

        super(threadExecutor, postExecutionThread);
        this.characterLocalRepository = characterLocalRepository;
        this.characterNetworkRepository = characterNetworkRepository;
    }

    @Override
    protected Observable<List<Character>> buildUseCaseObservable(@Nullable final Integer params) {
        return Observable.create(new ObservableOnSubscribe<List<Character>>() {
            @Override
            public void subscribe(final ObservableEmitter<List<Character>> emitter) {
                emitter.onNext(characterNetworkRepository.getCharacters(params));
            }
        });
    }

}

