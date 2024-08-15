import PageHeader from '@salesforce/design-system-react/components/page-header';
import PageHeaderControl from '@salesforce/design-system-react/components/page-header/control';
import Icon from '@salesforce/design-system-react/components/icon';
import Button from '@salesforce/design-system-react/components/button';

import { earningsAPI } from 'api';
import { useModal } from 'components/HOC/withModal';
import { useToasts } from 'components/HOC/withToasts';
import useEarnings from './useEarnings';
import EarningsTable from './earningsTable';
import EarningsForm from './earningsForm';

function HeaderActions(onNewClicked) {
  return (
    <PageHeaderControl>
      <Button label="Neu" onClick={() => onNewClicked()} responsive />
    </PageHeaderControl>
  );
}

function HeaderControls(onRefresh) {
  return (
    <PageHeaderControl>
      <Button
        assistiveText={{ icon: 'Refresh' }}
        iconCategory="utility"
        iconName="refresh"
        iconVariant="border-filled"
        variant="icon"
        onClick={onRefresh}
        responsive
      />
    </PageHeaderControl>
  );
}

export default function EarningsOverview() {
  const [earnings, loadEarnings, spinner, setLoading] = useEarnings();
  const [modal, showModal] = useModal();
  const [toast, showToast] = useToasts();

  const createEarning = async newEarning => {
    setLoading(true);
    try {
      await earningsAPI.createEarning(newEarning);
      loadEarnings();

      showToast('Erfolg!', 'Die Einnahme wurde erfolgreich gespeichert.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Die Einnahme konnte nicht gespeichert werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const updateEarning = async earningToUpdate => {
    setLoading(true);
    try {
      await earningsAPI.updateEarning(earningToUpdate);
      loadEarnings();

      showToast('Erfolg!', 'Die Einnahme wurde erfolgreich aktualisiert.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Die Einnahme konnte nicht aktualisiert werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const deleteEarning = async art => {
    setLoading(true);
    try {
      await earningsAPI.deleteEarning(art);
      loadEarnings();

      showToast('Erfolg!', 'Die Einnahme wurde erfolgreich gelöscht.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Die Einnahme konnte nicht gelöscht werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const openFormModal = earningToUpdate => {
    showModal({
      heading: `Einnahme ${earningToUpdate ? 'bearbeiten' : 'hinzufügen'}`,
      child: {
        type: EarningsForm,
        attributes: {
          presetEarning: earningToUpdate
        }
      },
      buttons: [
        {
          label: 'Abbrechen',
          variant: 'neutral'
        },
        {
          label: 'Speichern',
          variant: 'brand',
          action: childState => {
            if (earningToUpdate) {
              updateEarning(childState.item);
            } else {
              createEarning(childState.item);
            }
          }
        }
      ],
      options: {
        dismissOnClickOutside: false
      }
    });
  };

  return (
    <>
      {modal}
      {toast}
      {spinner}

      <PageHeader
        icon={<Icon category="standard" name="investment_account" />}
        label="Außerordentliche Einnahmen"
        title="Gesamt"
        truncate
        variant="object-home"
        info={`${earnings.length} Ergebnisse`}
        onRenderActions={() => HeaderActions(openFormModal)}
        onRenderControls={() => HeaderControls(loadEarnings)}
        className="slds-var-m-bottom_small"
      />

      <EarningsTable earnings={earnings} onUpdate={openFormModal} onDelete={deleteEarning} />
    </>
  );
}
