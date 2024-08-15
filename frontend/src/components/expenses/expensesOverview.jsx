import PageHeader from '@salesforce/design-system-react/components/page-header';
import PageHeaderControl from '@salesforce/design-system-react/components/page-header/control';
import Icon from '@salesforce/design-system-react/components/icon';
import Button from '@salesforce/design-system-react/components/button';

import { expensesAPI } from 'api';
import { useModal } from 'components/HOC/withModal';
import { useToasts } from 'components/HOC/withToasts';
import useExpenses from './useExpenses';
import ExpensesTable from './expensesTable';
import ExpensesForm from './expensesForm';

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

export default function ExpensesOverview() {
  const [expenses, loadExpenses, spinner, setLoading] = useExpenses();
  const [modal, showModal] = useModal();
  const [toast, showToast] = useToasts();

  const createExpense = async newExpense => {
    setLoading(true);
    try {
      await expensesAPI.createExpense(newExpense);
      loadExpenses();

      showToast('Erfolg!', 'Die Ausgabe wurde erfolgreich gespeichert.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Die Ausgabe konnte nicht gespeichert werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const updateExpense = async expenseToUpdate => {
    setLoading(true);
    try {
      await expensesAPI.updateExpense(expenseToUpdate);
      loadExpenses();

      showToast('Erfolg!', 'Die Ausgabe wurde erfolgreich aktualisiert.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Die Ausgabe konnte nicht aktualisiert werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const deleteExpense = async art => {
    setLoading(true);
    try {
      await expensesAPI.deleteExpense(art);
      loadExpenses();

      showToast('Erfolg!', 'Die Ausgabe wurde erfolgreich gelöscht.', 'success');
    } catch (error) {
      showToast('Ein Fehler ist aufgetreten!', 'Die Ausgabe konnte nicht gelöscht werden.', 'error');
    } finally {
      setLoading(false);
    }
  };

  const openFormModal = expenseToUpdate => {
    showModal({
      heading: `Ausgabe ${expenseToUpdate ? 'bearbeiten' : 'hinzufügen'}`,
      child: {
        type: ExpensesForm,
        attributes: {
          presetExpense: expenseToUpdate
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
            if (expenseToUpdate) {
              updateExpense(childState.item);
            } else {
              createExpense(childState.item);
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
        icon={<Icon category="standard" name="expense" />}
        label="Ausgaben"
        title="Gesamt"
        truncate
        variant="object-home"
        info={`${expenses.length} Ergebnisse`}
        onRenderActions={() => HeaderActions(openFormModal)}
        onRenderControls={() => HeaderControls(loadExpenses)}
        className="slds-var-m-bottom_small"
      />

      <ExpensesTable expenses={expenses} onUpdate={openFormModal} onDelete={deleteExpense} />
    </>
  );
}
